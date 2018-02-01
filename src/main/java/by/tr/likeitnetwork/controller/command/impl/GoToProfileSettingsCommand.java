package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.USER;
import static by.tr.likeitnetwork.controller.constant.JspPath.SETTINGS;

public class GoToProfileSettingsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToProfileSettingsCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        try{
            User user = ServiceFactory.getInstance().getUserService().findUserById(id);
            request.setAttribute(USER, user);

            request.setAttribute(AttributeKey.PASSWORD_CHANGED, request.getParameter(AttributeKey.PASSWORD_CHANGED));
            request.setAttribute(AttributeKey.PASSWORD_NOT_CHANGED, request.getParameter(AttributeKey.PASSWORD_NOT_CHANGED));
            request.setAttribute(AttributeKey.PROFILE_INFO_CHANGED, request.getParameter(AttributeKey.PROFILE_INFO_CHANGED));
            request.setAttribute(AttributeKey.PROFILE_INFO_NOT_CHANGED, request.getParameter(AttributeKey.PROFILE_INFO_NOT_CHANGED));

            request.getRequestDispatcher(SETTINGS).forward(request, response);
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
