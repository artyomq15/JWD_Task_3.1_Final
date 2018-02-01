package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.QueryConstructor;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.TopicServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTopicCommand implements Command {
    private final Logger logger = LogManager.getLogger(DeleteTopicCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer topicId = Integer.parseInt(request.getParameter(AttributeKey.TOPIC_ID));

        try {
            String redirect = RedirectQuery.MAIN;
            if (ServiceFactory.getInstance().getTopicService().deleteTopic(topicId)) {
                redirect = QueryConstructor.addParameter(redirect, AttributeKey.TOPIC_DELETED);
            } else {
                redirect = QueryConstructor.addParameter(redirect, AttributeKey.TOPIC_NOT_DELETED);
            }
            response.sendRedirect(redirect);
        } catch (TopicServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
