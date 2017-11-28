package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.command.help.UserHelper;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.JspPath.MAIN;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.USER;

public class GoToMainPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        HttpSession session = request.getSession(false);

        try{
            user = UserHelper.getProfileIfAuthorized(session);
            request.setAttribute(USER, user);

            //other info on main page

            request.getRequestDispatcher(MAIN).forward(request,response);
        } catch(ServiceException ex){
            LOGGER.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
