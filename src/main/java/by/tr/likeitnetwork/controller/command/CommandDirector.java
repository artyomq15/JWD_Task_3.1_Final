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
        dispatcherMap.put(CommandType.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
        dispatcherMap.put(CommandType.EXIT, new ExitCommand());


    }

    public CommandDirector(){}

    public Command getCommand(CommandType commandType){
        return dispatcherMap.get(commandType);
    }
}
