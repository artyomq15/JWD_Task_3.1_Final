package by.tr.likeitnetwork.controller.filter;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.controller.util.exception.TokenNotFoundException;
import by.tr.likeitnetwork.controller.util.exception.WrongTokensException;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;
import by.tr.likeitnetwork.controller.util.TokenParser;
import by.tr.likeitnetwork.controller.util.exception.InvalidTokenException;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try{
            checkTokens(request,response);
            filterChain.doFilter(request,response);
        } catch (AuthServiceException ex){
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }

    private void checkTokens(HttpServletRequest request, HttpServletResponse response) throws AuthServiceException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        AuthService authService = ServiceFactory.getInstance().getAuthService();

        try {
            String accessToken = CookieHandler.getToken(cookies, AttributeKey.ACCESS_TOKEN);
            if (authService.checkAccessTokenIsRight(accessToken)) {
                System.out.println("ACCESS TRUE");

                session.setAttribute(ROLE, User.Role.valueOf(TokenParser.parseRole(accessToken)).getRole());
                session.setAttribute(ID, TokenParser.parseId(accessToken));

                return;
            }

            String refreshToken = CookieHandler.getToken(cookies, AttributeKey.REFRESH_TOKEN);
            if (authService.checkRefreshTokenIsRight(refreshToken)) {
                System.out.println("REFRESH TRUE");
                AuthToken authToken = authService.refreshTokens(TokenParser.parseId(accessToken), TokenParser.parseRole(accessToken));

                CookieHandler.addToken(response, ACCESS_TOKEN, authToken.getAccessToken());
                CookieHandler.addToken(response, REFRESH_TOKEN, authToken.getRefreshToken());

                session.setAttribute(ROLE, User.Role.valueOf(TokenParser.parseRole(authToken.getAccessToken())).getRole());
                session.setAttribute(ID, TokenParser.parseId(authToken.getAccessToken()));

                return;
            }

            System.out.println("EKSIIIIT");
            throw new WrongTokensException();

        } catch (TokenNotFoundException | WrongTokensException | InvalidTokenException ex) {
            System.out.println("ROLE GUEST");
            session.setAttribute(ROLE, User.Role.GUEST.getRole());
            session.removeAttribute(ID);
            CookieHandler.removeTokens(response, cookies);
        }
    }

    @Override
    public void destroy() {

    }
}
