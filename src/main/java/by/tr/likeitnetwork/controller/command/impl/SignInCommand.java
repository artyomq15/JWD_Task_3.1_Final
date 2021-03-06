package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.QueryConstructor;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;

public class SignInCommand implements Command {
    private final Logger logger = LogManager.getLogger(SignInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInput input = new UserInput();
        input.setLogin(request.getParameter(LOGIN));
        input.setPassword(request.getParameter(PASSWORD));

        try {
            AuthToken tokens = ServiceFactory.getInstance().getAuthService().signIn(input);
            if (tokens != null) {
                CookieHandler.addToken(response, ACCESS_TOKEN, tokens.getAccessToken());
                CookieHandler.addToken(response, REFRESH_TOKEN, tokens.getRefreshToken());

                response.sendRedirect(RedirectQuery.MAIN);
            } else {
                String query = QueryConstructor.addParameter(RedirectQuery.SIGN_IN, AttributeKey.NOT_SIGNED_IN);
                response.sendRedirect(query);
            }
        } catch (ServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }

    }
}
