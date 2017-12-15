package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_SELECT_INFO_FOR_SIGN_IN = "SELECT user_id, user_password, user_password_salt, user_role FROM user WHERE user_login = ?;";

    public static final String SQL_UPDATE_TOKENS = "UPDATE auth SET access_token=?,access_token_expiration=now()+interval 2 hour,refresh_token=?,refresh_token_expiration=now()+interval 1 year WHERE user_id=?;";
    public static final String SQL_SELECT_ACCESS_TOKEN_TRUE = "SELECT user_id FROM auth WHERE access_token=? AND access_token_expiration>=now();";
    public static final String SQL_SELECT_REFRESH_TOKEN_TRUE = "SELECT user_id FROM auth WHERE refresh_token=? AND refresh_token_expiration>=now();";

    public static final String SQL_SELECT_ALL_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    public static final String SQL_INSERT_USER = "INSERT INTO user (user_name, user_email, user_login, user_password, user_password_salt) VALUES (?,?,?,?,?);";
    public static final String SQL_SELECT_USER_ID_BY_LOGIN = "SELECT user_id FROM user WHERE user_login=?;";

    public static final String SQL_SELECT_LANGUAGE_ID_BY_NAME = "SELECT lang_id FROM language WHERE lang_short_name=?;";
    public static final String SQL_SELECT_ALL_THEMES_IN_DEFAULT_LANGUAGE = "SELECT theme_id, theme_default_name FROM theme;";
    public static final String SQL_SELECT_ALL_THEMES_IN_LOCALE_LANGUAGE = "SELECT theme_id, locale_theme_name FROM theme_has_language WHERE lang_id=?;";



    private DAOQuery(){}
}
