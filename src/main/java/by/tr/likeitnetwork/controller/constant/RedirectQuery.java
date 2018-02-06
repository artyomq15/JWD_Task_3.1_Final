package by.tr.likeitnetwork.controller.constant;

public final class RedirectQuery {
    public static final String MAIN = "/NetworkController?command=go_to_main_page&page_number=1&count=10";

    public static final String SIGN_IN = "/NetworkController?command=go_to_sign_in";

    public static final String ERROR = "/NetworkController?command=go_to_error_page";
    public static final String SIGN_IN_WITH_MESSAGE = "/NetworkController?command=go_to_sign_in&message=";
    public static final String SIGN_UP_WITH_MESSAGE = "/NetworkController?command=go_to_sign_up&message=";

    private RedirectQuery(){}
}
