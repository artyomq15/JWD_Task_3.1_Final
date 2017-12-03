package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_SELECT_INFO_FOR_SIGN_IN = "SELECT user_id, user_password, user_password_salt, user_role FROM user WHERE user_login = ?;";

    public static final String SQL_UPDATE_TOKENS = "UPDATE auth SET access_token=?,access_token_expiration=now()+interval 2 hour,refresh_token=?,refresh_token_expiration=now()+interval 1 year WHERE user_id=?;";




    public static final String SQL_SELECT_ALL_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login = ?";
    public static final String SQL_SELECT_ALL_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    public static final String SQL_INSERT_USER = "INSERT INTO user (user_name, user_email, user_login, user_password, user_password_salt) VALUES (?,?,?,?,?);";
    public static final String SQL_SELECT_ID_BY_LOGIN = "SELECT user_id FROM user WHERE user_login=?;";
    public static final String SQL_INSERT_AUTH = "INSERT INTO auth (user_id) VALUES (?)";


    private DAOQuery(){}
}
