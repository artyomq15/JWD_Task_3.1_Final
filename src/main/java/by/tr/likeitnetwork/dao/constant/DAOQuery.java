package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_SELECT_ALL_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login = ?";
    public static final String SQL_SELECT_ALL_USER_BY_ID = "SELECT * FROM user WHERE id_user = ?";

    public static final String SQL_INSERT_USER = "INSERT INTO user (id_user, name_user, email_user ) VALUES (?,?,?)";
    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO account (login, password, salt, id_user) VALUES (?,?,?,?)";


    private DAOQuery(){}
}
