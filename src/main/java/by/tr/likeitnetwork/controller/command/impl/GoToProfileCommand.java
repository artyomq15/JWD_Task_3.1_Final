package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.message.MessageService;
import by.tr.likeitnetwork.service.topic.TopicService;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;
import static by.tr.likeitnetwork.controller.constant.JspPath.PROFILE;

public class GoToProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        User profileUser;

        String action = request.getParameter(ACTION);
        Integer profileUserId = Integer.parseInt(request.getParameter(AttributeKey.PROFILE_USER_ID));
        Integer userId = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        try {

            Integer role = (Integer) request.getSession().getAttribute(AttributeKey.ROLE);
            if (role > User.Role.GUEST.getRole()) {
                user = ServiceFactory.getInstance().getUserService().findUserById(userId);
                request.setAttribute(USER, user);
            }

            if (user != null && user.getId() == profileUserId) {
                request.setAttribute(PROFILE_USER, user);
            } else {
                profileUser = ServiceFactory.getInstance().getUserService().findUserById(profileUserId);
                request.setAttribute(PROFILE_USER, profileUser);
            }


            MessageService messageService = ServiceFactory.getInstance().getMessageService();
            TopicService topicService = ServiceFactory.getInstance().getTopicService();

            String localeLanguage = request.getSession().getAttribute(AttributeKey.LOCALE).toString();

            if (ACTION_GET_TOPICS_OF_USER.equals(action)) {
                List<Topic> topicList = topicService.getTopicsByUserId(localeLanguage, profileUserId);
                request.setAttribute(TOPIC_LIST, topicList);
            } else if (ACTION_GET_MESSAGES_OF_USER.equals(action)) {
                List<Message> messageList = messageService.getMessagesByUserId(localeLanguage, profileUserId);
                request.setAttribute(MESSAGE_LIST, messageList);
                List<Topic> topicList = topicService.getTopicsWhichCommendedByUser(localeLanguage, profileUserId);
                request.setAttribute(TOPIC_LIST_FOR_MESSAGES, topicList);
            }

            request.setAttribute(PROFILE_USER_TOPICS_COUNT, topicService.countTopicsOfUser(profileUserId));
            request.setAttribute(PROFILE_USER_MESSAGES_COUNT, messageService.countMessagesOfUser(profileUserId));

            request.getRequestDispatcher(PROFILE).forward(request, response);
        } catch (ServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }

    }
}
