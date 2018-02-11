package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignInCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(AttributeKey.NOT_SIGNED_IN, request.getParameter(AttributeKey.NOT_SIGNED_IN));
        request.setAttribute(AttributeKey.SIGNED_UP, request.getParameter(AttributeKey.SIGNED_UP));
        request.getRequestDispatcher(JspPath.SIGN_IN).forward(request, response);
    }
}
