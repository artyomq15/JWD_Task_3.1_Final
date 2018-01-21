package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ROLE;

public class ExitCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute(ROLE, User.Role.GUEST.getRole());
        session.removeAttribute(AttributeKey.ID);
        CookieHandler.removeTokens(response, request.getCookies());
        response.sendRedirect(RedirectQuery.MAIN);
    }
}
