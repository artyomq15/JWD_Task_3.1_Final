package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.theme.ThemeService;
import by.tr.likeitnetwork.service.topic.TopicService;
import by.tr.likeitnetwork.service.user.UserService;
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

public class GoToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ThemeService themeService = ServiceFactory.getInstance().getThemeService();
        TopicService topicService = ServiceFactory.getInstance().getTopicService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = null;
        List<Theme> themeList;
        List<Topic> topicList;
        Integer pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        Integer countTopic = Integer.parseInt(request.getParameter(COUNT));

        String localeLanguage = request.getSession().getAttribute(AttributeKey.LOCALE).toString();
        Integer userId = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        try {
            if (userId != null){
                user = userService.findUserById(userId);
            }
            request.setAttribute(USER, user);

            themeList = themeService.getAllThemes(localeLanguage);
            request.setAttribute(THEME_LIST, themeList);


            String expression = request.getParameter(EXPRESSION);
            if (expression != null){
                topicList = topicService.search(expression, localeLanguage, pageNumber, countTopic);
                request.setAttribute(EXPRESSION, expression);
            } else {
                String themeId = request.getParameter(THEME_ID);
                if (themeId == null) {
                    topicList = topicService.getAll(localeLanguage, pageNumber, countTopic);
                } else {
                    topicList = topicService.getTopicsByThemeId(localeLanguage, Integer.parseInt(themeId), pageNumber, countTopic);
                    if (!topicList.isEmpty()){
                        request.setAttribute(THEME, topicList.get(0).getTheme());
                    }

                }
            }
            request.setAttribute(TOPIC_LIST, topicList);
            request.setAttribute(PAGE_NUMBER, pageNumber);
            request.setAttribute(COUNT, countTopic);
            request.getRequestDispatcher(JspPath.MAIN).forward(request, response);
        } catch (ServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
