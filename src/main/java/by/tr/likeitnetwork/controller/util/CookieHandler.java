package by.tr.likeitnetwork.controller.util;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.exception.TokenNotFoundException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public final class CookieHandler {
    private static final int YEAR_IN_SECONDS = 60 * 60 * 24 * 365;

    public static final int COOKIE_LIFETIME = YEAR_IN_SECONDS;
    public static final int DELETE_COOKIE_LIFETIME = 0;

    public static String getLastRequest(Cookie[] cookies){
        String lastRequest = RedirectQuery.MAIN;
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(AttributeKey.LAST_REQUEST)){
                lastRequest = cookie.getValue();
                return lastRequest;
            }
        }
        return lastRequest;
    }

    public static String getToken(Cookie[] cookies, String tokenName) throws TokenNotFoundException{
        if (cookies==null){
            throw new TokenNotFoundException("Token not found");
        }
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(tokenName)){
                return cookie.getValue();
            }
        }
        throw new TokenNotFoundException("Token not found");
    }

    public static void addToken(HttpServletResponse response, String name, String body) {
        Cookie token = new Cookie(name, body);
        token.setMaxAge(COOKIE_LIFETIME);
        response.addCookie(token);
    }

    public static void removeTokens(HttpServletResponse response, Cookie[]cookies){
        if (cookies==null){
            return;
        }
        for (Cookie cookie: cookies){
            String cookieName = cookie.getName();
            if (cookieName.equals(AttributeKey.ACCESS_TOKEN) || cookieName.equals(AttributeKey.REFRESH_TOKEN)){
                removeToken(cookie, response);
            }
        }
    }

    private static void removeToken(Cookie token, HttpServletResponse response) {
        token.setMaxAge(DELETE_COOKIE_LIFETIME);
        response.addCookie(token);
    }

    private CookieHandler(){}
}
