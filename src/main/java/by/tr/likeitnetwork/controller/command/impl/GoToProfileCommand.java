package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.util.UserHelper;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ACCESS_TOKEN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.MESSAGE;
import static by.tr.likeitnetwork.controller.constant.JspPath.PROFILE;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.USER;

public class GoToProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        String message;

        Cookie[] cookies = request.getCookies();
        String accessToken = UserHelper.getTokenFromCookies(cookies, ACCESS_TOKEN);

        try{
            user = UserHelper.getProfileIfAuthorized(accessToken);
            request.setAttribute(USER, user);

            message = request.getParameter(MESSAGE);
            request.setAttribute(MESSAGE, message);


            //links to topics and messages

            request.getRequestDispatcher(PROFILE).forward(request,response);
        } catch (ServiceException ex){
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
