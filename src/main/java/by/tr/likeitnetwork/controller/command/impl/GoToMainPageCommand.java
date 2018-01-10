package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;
import by.tr.likeitnetwork.controller.util.CookieParser;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.util.UserHelper;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        User user;
        List<Theme> themeList;
        List<Topic> topicList;

        Cookie[] cookies = request.getCookies();
        String accessToken = CookieParser.getTokenFromCookies(cookies, ACCESS_TOKEN);
        try{
            user = UserHelper.getProfileIfAuthorized(accessToken);
            request.setAttribute(USER, user);

            String localeLanguage = request.getSession().getAttribute(AttributeKey.LOCALE).toString();

            themeList = ServiceFactory.getInstance().getThemeService().getAllThemes(localeLanguage);
            request.setAttribute(THEME_LIST, themeList);

            topicList = ServiceFactory.getInstance().getTopicService().getAll(localeLanguage);
            request.setAttribute(TOPIC_LIST, topicList);


            //other info on main page

            request.getRequestDispatcher(JspPath.MAIN).forward(request,response);
        } catch(ServiceException ex){
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }

    }
}
