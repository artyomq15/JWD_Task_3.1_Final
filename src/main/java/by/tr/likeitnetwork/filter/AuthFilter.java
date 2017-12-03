package by.tr.likeitnetwork.filter;

import by.tr.likeitnetwork.constant.CookieConstant;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.Role;
import by.tr.likeitnetwork.filter.exception.AuthFilterException;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;
import by.tr.likeitnetwork.util.UserHelper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ACCESS_TOKEN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.REFRESH_TOKEN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.ROLE;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        /*
        if request for pages: [(go_to_)sign in, sign up ]  or [commands for signing in and signing up]  => doFilter()
        else checking tokens


        OR

        if ROLE == null or ROLE = GUEST => doFilter
        else check tokens
        */

        Object roleValueObject = session.getAttribute(AttributeKey.ROLE);
        if (roleValueObject == null) {

            System.out.println("ROLE NULL");
            filterChain.doFilter(request, response);
        } else {
            Integer roleValue = Integer.parseInt(String.valueOf(roleValueObject));
            if (roleValue == Role.GUEST.getRole()) {

                System.out.println("ROLE " + roleValue);
                filterChain.doFilter(request, response);
            } else {

                AuthService authService = ServiceFactory.getInstance().getAuthService();
                Cookie[] cookies = request.getCookies();

                String accessToken = UserHelper.getTokenFromCookies(cookies, AttributeKey.ACCESS_TOKEN);
                try {
                    if (authService.checkAccessTokenIsRight(accessToken)) {

                        System.out.println("ACCESS TRUE");
                        filterChain.doFilter(request, response);
                    } else {
                        String refreshToken = UserHelper.getTokenFromCookies(cookies, AttributeKey.REFRESH_TOKEN);
                        if (authService.checkRefreshTokenIsRight(refreshToken)) {

                            System.out.println("REFRESH TRUE");
                            AuthToken authToken = authService.getNewTokensByOld(new AuthToken(accessToken, refreshToken));
                            if (authToken != null) {
                                Cookie accessCookie = new Cookie(ACCESS_TOKEN, accessToken);
                                accessCookie.setMaxAge(CookieConstant.ACCESS_COOKIE_LIFETIME);
                                response.addCookie(accessCookie);

                                Cookie refreshCookie = new Cookie(REFRESH_TOKEN, refreshToken);
                                refreshCookie.setMaxAge(CookieConstant.REFRESH_COOKIE_LIFETIME);
                                response.addCookie(refreshCookie);

                                filterChain.doFilter(request, response);
                            }
                        } else {

                            System.out.println("ROLE GUEST");
                            session.setAttribute(ROLE, Role.GUEST.getRole());
                            filterChain.doFilter(request, response);
                        }
                    }
                } catch (AuthServiceException ex) {
                    ////!!!!!!!!!!!
                    ex.printStackTrace();
                }
            }
        }




        /*
        checking tokens
        if == access-token => {
            doFilter()
            set ROLE = USER
        }
        if !=access-token => use refresh token

            if == refresh-token => {
                change both tokens in database and write them in cookies => do request
            }
            if != refresh-token => {
                redirect to sign in page
                set ROLE = GUEST
            }

         */


    }

    @Override
    public void destroy() {

    }
}
