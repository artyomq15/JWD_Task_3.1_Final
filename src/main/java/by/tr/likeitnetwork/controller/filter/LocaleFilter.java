package by.tr.likeitnetwork.controller.filter;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.LocaleServiceException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Object locale = session.getAttribute(AttributeKey.LOCALE);
        try {
            if (locale == null) {
                locale = request.getLocale().getLanguage();
                if (!ServiceFactory.getInstance().getLocaleService().checkLanguageExists(String.valueOf(locale))) {
                    locale = Locale.ENGLISH;
                }
                session.setAttribute(AttributeKey.LOCALE, locale);
            }

            filterChain.doFilter(request, response);
        } catch (LocaleServiceException ex) {
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }

    @Override
    public void destroy() {

    }
}
