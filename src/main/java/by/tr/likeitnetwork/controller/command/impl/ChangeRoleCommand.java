package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeRoleCommand implements Command{
    private final Logger logger = LogManager.getLogger(ChangeRoleCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter(AttributeKey.ID));
        User.Role roleValue = User.Role.valueOf(request.getParameter(AttributeKey.ROLE));

        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
            if ((Integer)request.getSession().getAttribute(AttributeKey.ROLE) != User.Role.SUPER_ADMIN.getRole()){
                response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
                return;
            } else if (User.Role.USER.equals(roleValue)){
                userService.setUserToAdmin(id);
            } else if (User.Role.ADMIN.equals(roleValue)){
                userService.setAdminToUser(id);
            }
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
