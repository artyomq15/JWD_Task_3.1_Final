package by.tr.likeitnetwork.filter.locale;

import by.tr.likeitnetwork.controller.constant.AttributeKey;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Object locale = session.getAttribute(AttributeKey.LOCALE);
        if (locale == null){
            locale = request.getLocale().getLanguage();
            session.setAttribute(AttributeKey.LOCALE, locale);
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
