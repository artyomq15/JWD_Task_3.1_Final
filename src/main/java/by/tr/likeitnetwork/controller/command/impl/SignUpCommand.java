package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserInput input = new UserInput();
        input.setLogin(request.getParameter(AttributeKey.LOGIN));
        input.setPassword(request.getParameter(AttributeKey.PASSWORD));
        input.setConfirmation(request.getParameter(AttributeKey.CONFIRMATION));
        input.setName(request.getParameter(AttributeKey.NAME));
        input.setEmail(request.getParameter(AttributeKey.EMAIL));


        boolean successful;
        try {
            successful = ServiceFactory.getInstance().getAuthService().signUp(input);

            if (successful) {
                response.sendRedirect(RedirectQuery.SIGN_IN);
            } else {
                response.sendRedirect(RedirectQuery.SIGN_UP_WITH_MESSAGE);
            }
        } catch (ServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }


    }
}
