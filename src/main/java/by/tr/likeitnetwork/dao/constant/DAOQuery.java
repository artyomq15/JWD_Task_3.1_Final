package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_CALL_GET_INFO_FOR_SIGN_IN_BY_LOGIN = "call getInfoForSignInByLogin(?, ?, ?, ?, ?)";
    public static final String SQL_CALL_UPDATE_TOKENS_BY_ID = "call updateTokensById(?, ?, ?)";

    public static final String SQL_SELECT_ACCESS_TOKEN_TRUE = "SELECT user_id FROM auth WHERE access_token=? AND access_token_expiration>=now();";
    public static final String SQL_SELECT_REFRESH_TOKEN_TRUE = "SELECT user_id FROM auth WHERE refresh_token=? AND refresh_token_expiration>=now();";

    public static final String SQL_SELECT_ALL_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    public static final String SELECT_PASSWORD_AND_SALT_BY_ID = "SELECT user_password, user_password_salt FROM user WHERE user_id = ?";
    public static final String SQL_UPDATE_NEW_PASSWORD_BY_ID = "UPDATE user SET user_password=? WHERE user_id=?;";

    public static final String SQL_CALL_CHANGE_PROFILE_INFO = "call changeProfileInfo(?, ?, ?, ?)";

    public static final String SQL_INSERT_USER = "INSERT INTO user (user_name, user_email, user_login, user_password, user_password_salt) VALUES (?,?,?,?,?);";

    public static final String SQL_CALL_GET_USER_ID_BY_LOGIN = "call getUserByLogin(?, ?)";

    public static final String SQL_SELECT_LANGUAGE_ID_BY_NAME = "SELECT lang_id FROM language WHERE lang_short_name=?;";

    public static final String SQL_CALL_GET_THEMES_BY_LANGUAGE = "call getAllThemes(?, ?, ?)";
    public static final String SQL_CALL_GET_THEME_BY_ID_AND_LANGUAGE = "call getThemeById(?, ?, ?)";

    public static final String SQL_CALL_GET_ALL_TOPICS = "call getAllTopics(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_ADD_TOPIC = "call addTopic(?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPIC_BY_ID = "call getTopicById(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_BY_THEME_ID = "call getTopicsByThemeId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_BY_USER_ID = "call getTopicsByUserId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_WHICH_COMMENTED_BY_USER = "call getTopicsWhichCommendedByUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SCL_CALL_COUNT_TOPICS_OF_USER = "call countTopicsOfUser(?, ?)";

    public static final String SQL_CALL_ADD_MESSAGE = "call addMessage(?, ?, ?)";
    public static final String SQL_CALL_DELETE_MESSAGE = "call deleteMessage(?)";
    public static final String SQL_CALL_LIKE_MESSAGE = "call likeMessage(?, ?)";
    public static final String SQL_CALL_UNLIKE_MESSAGE = "call unlikeMessage(?, ?)";
    public static final String SQL_CALL_GET_MESSAGES_BY_TOPIC_ID = "call getMessagesByTopicId(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_MESSAGES_BY_USER_ID = "call getMessagesByUserId(?, ?, ?, ?, ?, ?, ?)";
    public static final String SCL_CALL_COUNT_MESSAGES_OF_USER = "call countMessagesOfUser(?, ?)";


    private DAOQuery() {
    }
}
