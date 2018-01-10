package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieParser;
import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.MessageServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddMessageCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        String context = request.getParameter(AttributeKey.MESSAGE_CONTEXT);
        Integer topicId = Integer.parseInt(request.getParameter(AttributeKey.TOPIC_ID));

        Message message = new Message();
        User user = new User();
        user.setId(userId);
        message.setUser(user);
        message.setContext(context);
        message.setTopicId(topicId);
        try{
            if (ServiceFactory.getInstance().getMessageService().addMessage(message)){
                System.out.println("ADDED MESSAGE");
            } else {
                System.out.println("NOT ADDED MESSAGE");
            }

            response.sendRedirect(CookieParser.getLastRequest(request.getCookies()));
        } catch (MessageServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
