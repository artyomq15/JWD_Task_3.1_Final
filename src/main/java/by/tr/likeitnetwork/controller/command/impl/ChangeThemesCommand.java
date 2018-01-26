package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeThemesCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ThemeService themeService = ServiceFactory.getInstance().getThemeService();

        String russianName = request.getParameter(AttributeKey.RUSSIAN_LANGUAGE);
        String belarussianName = request.getParameter(AttributeKey.BELARUSSIAN_LANGUAGE);
        String englishName = request.getParameter(AttributeKey.ENGLISH_LANGUAGE);

        Map<String, String> localizedNames = new HashMap<>();
        localizedNames.put(AttributeKey.RUSSIAN_LANGUAGE, russianName);
        localizedNames.put(AttributeKey.BELARUSSIAN_LANGUAGE, belarussianName);
        localizedNames.put(AttributeKey.ENGLISH_LANGUAGE, englishName);

        String action = request.getParameter(AttributeKey.ACTION);
        try {
            if (AttributeKey.ACTION_ADD_THEME.equals(action)) {
                themeService.addTheme(localizedNames);
            } else if (AttributeKey.ACTION_CHANGE_THEME.equals(action)) {
                Integer id = Integer.parseInt(request.getParameter(AttributeKey.THEME_ID));
                themeService.updateTheme(id, localizedNames);
            }
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (ThemeServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
