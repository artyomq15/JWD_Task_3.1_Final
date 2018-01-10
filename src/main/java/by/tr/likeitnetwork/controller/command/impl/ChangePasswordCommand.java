package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieParser;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.util.UserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ACCESS_TOKEN;


public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter(AttributeKey.PASSWORD);
        String newPassword = request.getParameter(AttributeKey.NEW_PASSWORD);
        String newPasswordConfirmation = request.getParameter(AttributeKey.CONFIRMATION);

        Cookie[] cookies = request.getCookies();
        String accessToken = CookieParser.getTokenFromCookies(cookies, ACCESS_TOKEN);
        Integer id = UserHelper.parseIdFromToken(accessToken);

        try {
            boolean success = ServiceFactory.getInstance().getUserService().changePassword(id, oldPassword, newPassword, newPasswordConfirmation);
            if (success) {
                response.sendRedirect(RedirectQuery.PROFILE);
            } else {
                response.sendRedirect(RedirectQuery.PROFILE_WITH_MESSAGE);
            }
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
