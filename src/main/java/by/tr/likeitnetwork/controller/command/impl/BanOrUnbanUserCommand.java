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

public class BanOrUnbanUserCommand implements Command {
    private final Logger logger = LogManager.getLogger(BanOrUnbanUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(AttributeKey.ACTION);
        Integer id = Integer.parseInt(request.getParameter(AttributeKey.ID));
        Integer roleValue = User.Role.valueOf(request.getParameter(AttributeKey.ROLE)).getRole();

        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
            if (roleValue >= (Integer) request.getSession().getAttribute(AttributeKey.ROLE)){
                response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
                return;
            }

            if (action.equals(AttributeKey.ACTION_BAN)){
                userService.banUser(id);
            } else if (action.equals(AttributeKey.ACTION_UNBAN)){
                userService.unbanUser(id);
            }
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
