package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.JspPath;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;
import by.tr.likeitnetwork.service.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.*;

public class GoToAdminPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToAdminPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        Integer userId = (Integer) request.getSession().getAttribute(AttributeKey.ID);

        try {
            if (userId == null) {
                response.sendRedirect(RedirectQuery.MAIN);
                return;
            }
            user = ServiceFactory.getInstance().getUserService().findUserById(userId);
            if (user.getRole().getRole() == User.Role.USER.getRole()) {
                response.sendRedirect(RedirectQuery.MAIN);
                return;
            }
            request.setAttribute(USER, user);

            String action = request.getParameter(ACTION);
            if (ACTION_THEMES_LISTS.equals(action)) {
                request.setAttribute(ACTION_THEMES_LISTS, action);
                handleThemesList(request);
            } else {
                handleUsersList(request, action);
            }

            request.getRequestDispatcher(JspPath.ADMIN).forward(request, response);
        } catch (UserServiceException | ThemeServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }

    private void handleThemesList(HttpServletRequest request) throws ThemeServiceException {
        final int IS_BANNED_INDEX = 4;

        List<List<Object>> themeList = ServiceFactory.getInstance().getThemeService().getThemesInfo();
        List<List<Object>> shownThemes = new ArrayList<>();
        List<List<Object>> hiddenThemes = new ArrayList<>();

        for (List<Object> list : themeList) {
            if ((Boolean) list.get(IS_BANNED_INDEX)) {
                hiddenThemes.add(list);
            } else {
                shownThemes.add(list);
            }
        }
        request.setAttribute(SHOWN_THEMES, shownThemes);
        request.setAttribute(HIDDEN_THEMES, hiddenThemes);
    }

    private void handleUsersList(HttpServletRequest request, String action) throws UserServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        String expression = request.getParameter(EXPRESSION);
        Integer pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        Integer countUser = Integer.parseInt(request.getParameter(COUNT));
        List<User> searchList = null;

        if (ACTION_NOT_BANNED.equals(action)) {
            searchList = userService.findNotBannedUsers(pageNumber, countUser);
            request.setAttribute(ACTION_NOT_BANNED, ACTION_NOT_BANNED);
        } else if (ACTION_BANNED.equals(action)) {
            searchList = userService.findBannedUsers(pageNumber, countUser);
            request.setAttribute(ACTION_BANNED, ACTION_BANNED);
        } else if (ACTION_ADMINS.equals(action)) {
            searchList = userService.findAdmins(pageNumber, countUser);
            request.setAttribute(ACTION_ADMINS, ACTION_ADMINS);
        } else if (expression != null) {
            searchList = userService.findUsersByNameOrLogin(expression, pageNumber, countUser);
            request.setAttribute(EXPRESSION, expression);
        }
        request.setAttribute(SEARCH_USER_LIST, searchList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(COUNT, countUser);
    }
}
