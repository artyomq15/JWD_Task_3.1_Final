package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.MessageServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RateMessageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RateMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        Integer messageId = Integer.parseInt(request.getParameter(AttributeKey.MESSAGE_ID));
        String action = request.getParameter(AttributeKey.ACTION);
        try {
            if (action.equals(AttributeKey.ACTION_LIKE)){
                ServiceFactory.getInstance().getMessageService().likeMessage(messageId, id);
            } else if (action.equals(AttributeKey.UNLIKE_ACTION)) {
                ServiceFactory.getInstance().getMessageService().unlikeMessage(messageId, id);
            }


            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));

        } catch (MessageServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
