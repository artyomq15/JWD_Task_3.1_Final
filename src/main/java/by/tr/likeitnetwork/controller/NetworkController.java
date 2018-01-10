package by.tr.likeitnetwork.controller;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.command.CommandDirector;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.CommandType;
import by.tr.likeitnetwork.controller.constant.QueryConstructor;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(NetworkController.class);


    @Override
    public void init() throws ServletException {
        try {
            ServiceFactory.initService();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandParameter = request.getParameter(AttributeKey.COMMAND);
        CommandType commandType = CommandType.valueOf(commandParameter.toUpperCase().trim());

        CommandDirector commandDirector = new CommandDirector();
        Command command = commandDirector.getCommand(commandType);

        if (commandType != CommandType.CHANGE_LOCALE) {
            String lastRequest = request.getServletPath() + QueryConstructor.SIGN_BEFORE_ATTRIBUTES + request.getQueryString();
            response.addCookie(new Cookie(AttributeKey.LAST_REQUEST, lastRequest));
        }

        command.execute(request, response);

    }

    @Override
    public void destroy() {
        try {
            ServiceFactory.destroyService();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
