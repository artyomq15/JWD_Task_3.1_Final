package by.tr.likeitnetwork.controller.util;

import by.tr.likeitnetwork.controller.util.exception.InvalidTokenException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenParser {
    private static final Pattern TOKEN_INFO = Pattern.compile("(\\d+):([a-z0-9]+):([A-Z]+)");
    private static final int ID_GROUP = 1;
    public static final int ROLE_GROUP = 3;

    public static Integer parseId(String token) throws InvalidTokenException{
        Matcher matcher = TOKEN_INFO.matcher(token);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(ID_GROUP));
        }
        throw new InvalidTokenException();
    }

    public static String parseRole(String token) throws InvalidTokenException{
        Matcher matcher = TOKEN_INFO.matcher(token);
        if (matcher.find()){
            return matcher.group(ROLE_GROUP);
        }
        throw new InvalidTokenException();
    }
}
