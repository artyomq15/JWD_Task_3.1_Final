package by.tr.likeitnetwork.util;

import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import javax.naming.event.ObjectChangeListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ACCESS_TOKEN;

public class UserHelper {
    private static final Pattern TOKEN_INFO = Pattern.compile("(\\d+):([a-z0-9]+):([A-Z]+)");
    private static final int ID_GROUP = 1;
    private static final int TOKEN_GROUP = 2;
    public static final int ROLE_GROUP = 3;

    public static User getProfileIfAuthorized(String token) throws UserServiceException{
        if (token==null){
            return null;
        }
        Integer id = parseIdFromToken(token);
        if (id == null){
            return null;
        }
        return ServiceFactory.getInstance().getUserService().findUserById(id);
    }

    public static String getTokenFromCookies(Cookie[] cookies, String tokenName){
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(tokenName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static Integer parseIdFromToken(String token){
        Matcher matcher = TOKEN_INFO.matcher(token);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(ID_GROUP));
        }
        return null;
    }

    public static String parseRoleFromToken(String token){
        Matcher matcher = TOKEN_INFO.matcher(token);
        if (matcher.find()){
            return matcher.group(ROLE_GROUP);
        }
        return null;
    }
}
