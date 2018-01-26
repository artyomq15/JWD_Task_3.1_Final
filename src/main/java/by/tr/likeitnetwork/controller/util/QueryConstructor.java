package by.tr.likeitnetwork.controller.util;

import by.tr.likeitnetwork.controller.constant.AttributeKey;

import java.util.ArrayList;
import java.util.List;

public final class QueryConstructor {
    public static final String SIGN_BEFORE_ATTRIBUTES = "?";
    public static final String PARAMETER_DIVIDER = "&";
    public static final String PARAMETER_VALUE_DIVIDER = "=";

    private static List<String> uiFlags = new ArrayList<>();

    static{
        uiFlags.add(AttributeKey.MESSAGE_ADDED);
        uiFlags.add(AttributeKey.MESSAGE_NOT_ADDED);
        uiFlags.add(AttributeKey.TOPIC_ADDED);
        uiFlags.add(AttributeKey.TOPIC_NOT_ADDED);
    }

    public static String clearQueryFromUIFlags(String query){
        for (String flag : uiFlags){
            if (query.contains(flag)){
                query = query.replace("&" + flag, "");
            }
        }
        return query;
    }

    public static String addParameter(String query, String parameter){
        return query + PARAMETER_DIVIDER + parameter;
    }

    private QueryConstructor(){}
}
