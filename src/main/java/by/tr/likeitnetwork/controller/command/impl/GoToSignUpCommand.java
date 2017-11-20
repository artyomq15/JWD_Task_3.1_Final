package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignUpCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter(AttributeKey.MESSAGE);

        request.setAttribute(AttributeKey.MESSAGE, message);
        request.getRequestDispatcher(JspPath.SIGN_UP).forward(request, response);
    }
}
