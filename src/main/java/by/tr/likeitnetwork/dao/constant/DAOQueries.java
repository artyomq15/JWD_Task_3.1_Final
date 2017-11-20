package by.tr.likeitnetwork.dao.constant;


public final class DAOQueries {
    public static final String GET_ACCOUNT_INFO = "SELECT * FROM account WHERE login = ?";
    public static final String GET_USER_INFO = "SELECT * FROM user WHERE id_user = ?";

    public static final String ADD_USER = "INSERT INTO user (id_user, name_user, email_user ) VALUES (?,?,?)";
    public static final String ADD_ACCOUNT = "INSERT INTO account (login, password, salt, id_user) VALUES (?,?,?,?)";


    private DAOQueries(){}
}
