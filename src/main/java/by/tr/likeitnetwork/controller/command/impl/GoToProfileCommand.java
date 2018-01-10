package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.util.CookieParser;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.util.UserHelper;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;
import static by.tr.likeitnetwork.controller.constant.JspPath.PROFILE;

public class GoToProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        Integer profileUserId;
        User profileUser;
        String message;

        Cookie[] cookies = request.getCookies();
        String accessToken = CookieParser.getTokenFromCookies(cookies, ACCESS_TOKEN);



        profileUserId = Integer.parseInt(request.getParameter(AttributeKey.PROFILE_USER_ID));
        try{

            Integer role = (Integer) request.getSession().getAttribute(AttributeKey.ROLE);
            if (role > User.Role.GUEST.getRole()){
                user = UserHelper.getProfileIfAuthorized(accessToken);
                request.setAttribute(USER, user);
            }

            if (user != null && user.getId() == profileUserId){
                request.setAttribute(PROFILE_USER, user);
            } else {
                profileUser = ServiceFactory.getInstance().getUserService().findUserById(profileUserId);
                request.setAttribute(PROFILE_USER, profileUser);


            }
            //topics & messages of profileUserId





            /* changing password
            message = request.getParameter(MESSAGE);

            request.setAttribute(MESSAGE, message);
            */


            //links to topics and messages

            request.getRequestDispatcher(PROFILE).forward(request,response);
        } catch (ServiceException ex){
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
