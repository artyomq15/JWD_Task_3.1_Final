package by.tr.likeitnetwork.controller.command;

import by.tr.likeitnetwork.controller.command.impl.*;
import by.tr.likeitnetwork.controller.constant.CommandType;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
    private Map<CommandType, Command> dispatcherMap = new HashMap<>();

    {
        dispatcherMap.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());

        dispatcherMap.put(CommandType.GO_TO_SIGN_IN, new GoToSignInCommand());
        dispatcherMap.put(CommandType.GO_TO_SIGN_UP, new GoToSignUpCommand());
        dispatcherMap.put(CommandType.SIGN_IN, new SignInCommand());
        dispatcherMap.put(CommandType.SIGN_UP, new SignUpCommand());

        dispatcherMap.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());

        dispatcherMap.put(CommandType.GO_TO_PROFILE, new GoToProfileCommand());
        dispatcherMap.put(CommandType.GO_TO_PROFILE_SETTINGS, new GoToProfileSettingsCommand());

        dispatcherMap.put(CommandType.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());

        dispatcherMap.put(CommandType.EXIT, new ExitCommand());

        dispatcherMap.put(CommandType.CHANGE_PASSWORD, new ChangePasswordCommand());
        dispatcherMap.put(CommandType.CHANGE_PROFILE_INFO, new ChangeProfileInformationCommand());

        dispatcherMap.put(CommandType.ADD_TOPIC, new AddTopicCommand());
        dispatcherMap.put(CommandType.DELETE_TOPIC, new DeleteTopicCommand());
        dispatcherMap.put(CommandType.GO_TO_TOPIC_PAGE, new GoToTopicPage());

        dispatcherMap.put(CommandType.RATE_MESSAGE, new RateMessageCommand());
        dispatcherMap.put(CommandType.ADD_MESSAGE, new AddMessageCommand());
        dispatcherMap.put(CommandType.DELETE_MESSAGE, new DeleteMessageCommand());

        dispatcherMap.put(CommandType.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
        dispatcherMap.put(CommandType.BAN_UNBAN, new BanOrUnbanUserCommand());
        dispatcherMap.put(CommandType.CHANGE_ROLE, new ChangeRoleCommand());

        dispatcherMap.put(CommandType.SHOW_HIDE_THEME, new ShowOrHideThemeCommand());
        dispatcherMap.put(CommandType.CHANGE_THEMES, new ChangeThemesCommand());


    }

    public CommandDirector(){}

    public Command getCommand(CommandType commandType){
        return dispatcherMap.get(commandType);
    }
}
