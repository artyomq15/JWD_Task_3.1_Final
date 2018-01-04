package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
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

import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.exception.TopicServiceException;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.util.UserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.THEME_LIST;


public class GoToTopicPage implements Command {
    private static final Logger logger = LogManager.getLogger(GoToTopicPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeLanguage = request.getSession().getAttribute(AttributeKey.LOCALE).toString();
        Integer topicId = Integer.parseInt(request.getParameter(AttributeKey.TOPIC_ID));
        Cookie[] cookies = request.getCookies();
        String accessToken = UserHelper.getTokenFromCookies(cookies, AttributeKey.ACCESS_TOKEN);
        User user;
        List<Theme> themeList;
        try {
            user = UserHelper.getProfileIfAuthorized(accessToken);
            request.setAttribute(AttributeKey.USER, user);

            themeList = ServiceFactory.getInstance().getThemeService().getAllThemes(localeLanguage);
            request.setAttribute(THEME_LIST, themeList);

            Topic topic = ServiceFactory.getInstance().getTopicService().getTopicById(localeLanguage, topicId);
            request.setAttribute(AttributeKey.TOPIC, topic);

            request.getRequestDispatcher(JspPath.TOPIC).forward(request, response);
        } catch (TopicServiceException | UserServiceException | ThemeServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
