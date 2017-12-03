package by.tr.likeitnetwork.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        filterChain.doFilter(request, response);


        /*cookie.setMaxAge(60 * 60 * 24 * 365);  year life cookie/*

        /*
        if request for pages: [(go_to_)sign in, sign up ]  or [commands for signing in and signing up]  => doFilter()
        else checking tokens


        OR

        if ROLE == null or ROLE = GUEST => doFilter
        else check tokens
        */

        /*
        checking tokens
        if == access-token => {
            doFilter()
            set ROLE = USER
        }
        if !=access-token => use refresh token

            if == refresh-token => {
                change both tokens in database and write them in cookies => do request
            }
            if != refresh-token => {
                redirect to sign in page
                set ROLE = GUEST
            }

         */



    }

    @Override
    public void destroy() {

    }
}
