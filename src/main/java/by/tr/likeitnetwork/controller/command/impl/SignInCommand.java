package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.Role;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.LOGIN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.PASSWORD;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.ROLE;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.ID;

public class SignInCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try{
            String id = ServiceFactory.getInstance().getAuthService().signIn(login, password);

            if (id != null){
                //will be changes in roles
                request.getSession().setAttribute(ROLE, Role.USER.getRole());
                request.getSession().setAttribute(ID, id);

                response.sendRedirect(RedirectQuery.MAIN);
            } else {
                response.sendRedirect(RedirectQuery.SIGN_IN_WITH_MESSAGE);
            }
        } catch (ServiceException ex){
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
