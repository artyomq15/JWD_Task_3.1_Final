package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.constant.CookieConstant;
import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.util.UserHelper;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.Role;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;

public class SignInCommand implements Command {
    private final Logger LOGGER = LogManager.getLogger(SignInCommand.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            AuthToken tokens = ServiceFactory.getInstance().getAuthService().signIn(login, password);
            if (tokens != null) {
                String accessToken = tokens.getAccessToken();
                String refreshToken = tokens.getRefreshToken();
                //will be changes in roles
                request.getSession().setAttribute(ROLE, Role.valueOf(UserHelper.parseRoleFromToken(accessToken)).getRole());
                //request.getSession().setAttribute(ID, UserHelper.parseIdFromToken(accessToken));

                Cookie accessCookie = new Cookie(ACCESS_TOKEN, accessToken);
                accessCookie.setMaxAge(CookieConstant.ACCESS_COOKIE_LIFETIME);
                response.addCookie(accessCookie);

                Cookie refreshCookie = new Cookie(REFRESH_TOKEN, refreshToken);
                refreshCookie.setMaxAge(CookieConstant.REFRESH_COOKIE_LIFETIME );
                response.addCookie(refreshCookie);

                response.sendRedirect(RedirectQuery.MAIN);
            } else {
                response.sendRedirect(RedirectQuery.SIGN_IN_WITH_MESSAGE);
            }
        } catch (ServiceException ex) {
            LOGGER.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
