package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_CALL_GET_INFO_FOR_SIGN_IN_BY_LOGIN = "call getInfoForSignInByLogin(?, ?, ?, ?, ?)";
    public static final String SQL_CALL_UPDATE_TOKENS_BY_ID = "call updateTokensById(?, ?, ?)";

    public static final String SQL_CALL_GET_ID_BY_ACCESS_TOKEN = "call getIdByAccessToken(?, ?)";
    public static final String SQL_CALL_GET_ID_BY_REFRESH_TOKEN = "call getIdByRefreshToken(?, ?)";

    public static final String SQL_CALL_GET_USER_BY_ID = "call getUserById(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_USER_BY_NAME_OR_LOGIN = "call getUserByLoginOrName(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_USER_BY_BANNED_STATE = "call getUsersByBannedState(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_ADMINS = "call getAdmins(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_UPDATE_IMG = "call updateUserImg(?, ?)";

    public static final String SQL_CALL_BAN_USER = "call banUser(?)";
    public static final String SQL_CALL_UNBAN_USER = "call unbanUser(?)";
    public static final String SQL_CALL_SET_USER_TO_ADMIN = "call setUserToAdmin(?)";
    public static final String SQL_CALL_SET_ADMIN_TO_USER = "call setAdminToUser(?)";


    public static final String SQL_CALL_GET_PASSWORD_AND_SALT_BY_ID = "call getPasswordAndSaltById(?, ?, ?)";
    public static final String SQL_CALL_UPDATE_PASSWORD_BY_ID = "call updatePasswordById(?, ?)";

    public static final String SQL_CALL_CHANGE_PROFILE_INFO = "call changeProfileInfo(?, ?, ?, ?)";

    public static final String SQL_CALL_ADD_USER = "call addUser(?, ?, ?, ?, ?)";

    public static final String SQL_CALL_GET_USER_ID_BY_LOGIN = "call getUserByLogin(?, ?)";

    public static final String SQL_CALL_GET_LANGUAGE_ID_BY_NAME = "call getLanguageIdByName(?, ?)";

    public static final String SQL_CALL_GET_THEMES_BY_LANGUAGE = "call getAllThemes(?, ?, ?)";
    public static final String SQL_CALL_GET_THEME_BY_ID_AND_LANGUAGE = "call getThemeById(?, ?, ?)";
    public static final String SQL_CALL_GET_THEMES_INFO = "call getThemesInfo(?, ?, ?, ?, ?)";
    public static final String SQL_CALL_SHOW_THEME = "call showTheme(?)";
    public static final String SQL_CALL_HIDE_THEME = "call hideTheme(?)";
    public static final String SQL_CALL_GET_ADDED_THEME_ID = "call addNewThemeId(?)";
    public static final String SQL_CALL_DELETE_THEME = "call deleteTheme(?)";
    public static final String SQL_CALL_INSERT_LOCALIZED_THEME_NAME = "call addThemeLocalizedName(?, ?, ?)";
    public static final String SQL_CALL_UPDATE_LOCALIZED_THEME_NAME = "call updateThemeLocalizedName(?, ?, ?)";

    public static final String SQL_CALL_GET_ALL_TOPICS = "call getAllTopics(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_SEARCH_TOPICS = "call searchTopics(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_ADD_TOPIC = "call addTopic(?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPIC_BY_ID = "call getTopicById(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_BY_THEME_ID = "call getTopicsByThemeId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_BY_USER_ID = "call getTopicsByUserId(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPICS_WHICH_COMMENTED_BY_USER = "call getTopicsWhichCommendedByUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SCL_CALL_COUNT_TOPICS_OF_USER = "call countTopicsOfUser(?, ?)";
    public static final String SQL_CALL_DELETE_TOPIC = "call deleteTopic(?)";

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
