package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.CONFIRMATION;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.PASSWORD;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.LOGIN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.NAME;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.EMAIL;

public class SignUpCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getParameter(CONFIRMATION);
        String password = request.getParameter(PASSWORD);

        boolean confirm = confirmation.equals(password);
        if (!confirm){
            response.sendRedirect(RedirectQuery.SIGN_UP_WITH_MESSAGE);
        } else{
            RegistrationInfo registrationInfo = new RegistrationInfo();
            registrationInfo.setLogin(request.getParameter(LOGIN));
            registrationInfo.setPassword(request.getParameter(PASSWORD));
            registrationInfo.setName(request.getParameter(NAME));
            registrationInfo.setEmail(request.getParameter(EMAIL));

            boolean successful;
            try{
                successful = ServiceFactory.getInstance().getAuthService().register(registrationInfo);

                if (successful){
                    response.sendRedirect(RedirectQuery.SIGN_IN);
                } else{
                    response.sendRedirect(RedirectQuery.SIGN_UP_WITH_MESSAGE);
                }
            } catch (ServiceException ex){
                response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE + ex.getMessage());
            }

        }





    }
}
