package by.tr.likeitnetwork.controller.util;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public final class CookieParser {
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

    public static String getTokenFromCookies(Cookie[] cookies, String tokenName){
        if (cookies==null){
            return null;
        }
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(tokenName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void removeTokensFromCookies(HttpServletResponse response, Cookie[]cookies){
        if (cookies==null){
            return;
        }

        for (Cookie cookie: cookies){
            String cookieName = cookie.getName();
            if (cookieName.equals(AttributeKey.ACCESS_TOKEN) || cookieName.equals(AttributeKey.REFRESH_TOKEN)){
                removeToken(cookieName, response);
            }
        }
    }

    private static void removeToken(String tokenName, HttpServletResponse response) {
        Cookie cookie = new Cookie(tokenName, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private CookieParser(){}
}
