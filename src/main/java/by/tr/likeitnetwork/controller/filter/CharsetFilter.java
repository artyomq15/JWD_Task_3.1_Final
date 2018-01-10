package by.tr.likeitnetwork.controller.filter;

import javax.servlet.*;
import java.io.IOException;


public class CharsetFilter implements Filter {
    private final String REQUEST_ENCODING = "requestEncoding";
    private final String UTF8_ENCODING = "UTF-8";
    private final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(REQUEST_ENCODING);
        if (encoding == null) {
            encoding = UTF8_ENCODING;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }

        servletResponse.setContentType(CONTENT_TYPE);
        servletResponse.setCharacterEncoding(UTF8_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
