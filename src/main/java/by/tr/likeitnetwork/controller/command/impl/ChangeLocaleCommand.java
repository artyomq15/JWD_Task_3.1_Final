package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.util.CookieParser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.LOCALE;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.LAST_REQUEST;

import static by.tr.likeitnetwork.controller.constant.RedirectQuery.MAIN;

public class ChangeLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute(LOCALE, request.getParameter(LOCALE));
        response.sendRedirect(CookieParser.getLastRequest(request.getCookies()));
    }
}
