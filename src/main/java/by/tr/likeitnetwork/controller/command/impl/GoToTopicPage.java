package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import by.tr.likeitnetwork.service.exception.MessageServiceException;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.exception.TopicServiceException;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GoToTopicPage implements Command {
    private static final Logger logger = LogManager.getLogger(GoToTopicPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        String localeLanguage = request.getSession().getAttribute(AttributeKey.LOCALE).toString();
        Integer topicId = Integer.parseInt(request.getParameter(AttributeKey.TOPIC_ID));

        User user = null;
        List<Theme> themeList;
        List<Message> messageList;
        try {
            if (userId != null){
                user = ServiceFactory.getInstance().getUserService().findUserById(userId);
            }
            request.setAttribute(AttributeKey.USER, user);

            themeList = ServiceFactory.getInstance().getThemeService().getAllThemes(localeLanguage);
            request.setAttribute(AttributeKey.THEME_LIST, themeList);

            Topic topic = ServiceFactory.getInstance().getTopicService().getTopicById(localeLanguage, topicId);
            request.setAttribute(AttributeKey.TOPIC, topic);


            messageList = ServiceFactory.getInstance().getMessageService().getMessagesByTopicId(localeLanguage, topicId);
            request.setAttribute(AttributeKey.MESSAGE_LIST, messageList);


            request.setAttribute(AttributeKey.MESSAGE_ADDED, request.getParameter(AttributeKey.MESSAGE_ADDED));
            request.setAttribute(AttributeKey.MESSAGE_NOT_ADDED, request.getParameter(AttributeKey.MESSAGE_NOT_ADDED));



            request.getRequestDispatcher(JspPath.TOPIC).forward(request, response);
        } catch (TopicServiceException | UserServiceException | ThemeServiceException | MessageServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
