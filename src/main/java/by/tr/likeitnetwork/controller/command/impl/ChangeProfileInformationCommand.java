package by.tr.likeitnetwork.controller.command.impl;


import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.controller.util.QueryConstructor;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ChangeProfileInformationCommand implements Command{
    private static final Logger logger = LogManager.getLogger(ChangeProfileInformationCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(AttributeKey.ID);

        UserInput input = new UserInput();
        input.setName(request.getParameter(AttributeKey.NAME));
        input.setEmail(request.getParameter(AttributeKey.EMAIL));
        input.setAboutUser(request.getParameter(AttributeKey.ABOUT));
        try {
            boolean success = ServiceFactory.getInstance().getUserService().changeProfileInfo(id, input);
            String lastRequest = CookieHandler.getLastRequest(request.getCookies());
            if (success) {
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.PROFILE_INFO_CHANGED);
            } else {
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.PROFILE_INFO_NOT_CHANGED);
            }
            response.sendRedirect(lastRequest);
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
