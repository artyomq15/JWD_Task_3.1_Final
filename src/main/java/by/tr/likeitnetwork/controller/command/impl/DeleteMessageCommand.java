package by.tr.likeitnetwork.controller.command.impl;


import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.MessageServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DeleteMessageCommand implements Command{
    private final Logger logger = LogManager.getLogger(DeleteMessageCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer messageId = Integer.parseInt(request.getParameter(AttributeKey.MESSAGE_ID));
        try{
            ServiceFactory.getInstance().getMessageService().deleteMessage(messageId);
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (MessageServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
