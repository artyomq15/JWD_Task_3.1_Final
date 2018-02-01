package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.ThemeServiceException;
import by.tr.likeitnetwork.service.theme.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowOrHideThemeCommand implements Command {
    private final Logger logger = LogManager.getLogger(BanOrUnbanUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(AttributeKey.ACTION);
        Integer themeId = Integer.parseInt(request.getParameter(AttributeKey.THEME_ID));
        ThemeService themeService = ServiceFactory.getInstance().getThemeService();
        try{
            if (AttributeKey.ACTION_SHOW_THEME.equals(action)){
                themeService.showTheme(themeId);
            } else if (AttributeKey.ACTION_HIDE_THEME.equals(action)){
                themeService.hideTheme(themeId);
            }
            response.sendRedirect(CookieHandler.getLastRequest(request.getCookies()));
        } catch (ThemeServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }

    }
}
