package by.tr.likeitnetwork.controller.constant;

public final class RedirectQuery {
    public static final String MAIN = "/NetworkController?command=go_to_main_page&page_number=1&count=10";

    public static final String PROFILE_SETTINGS = "/NetworkController?command=go_to_profile&action=get_settings&profile_user_id=";
    public static final String PROFILE_SETTINGS_WITH_MESSAGE = "/NetworkController?command=go_to_profile&message=&action=get_settings&profile_user_id=";

    public static final String SIGN_IN = "/NetworkController?command=go_to_sign_in";
    public static final String SIGN_UP = "/NetworkController?command=go_to_sign_up";

    public static final String ERROR_WITH_MESSAGE = "/NetworkController?command=go_to_error_page&message=";
    public static final String SIGN_IN_WITH_MESSAGE = "/NetworkController?command=go_to_sign_in&message=";
    public static final String SIGN_UP_WITH_MESSAGE = "/NetworkController?command=go_to_sign_up&message=";

    private RedirectQuery(){}
}
