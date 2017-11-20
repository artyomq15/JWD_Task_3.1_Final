package by.tr.likeitnetwork.service;

import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.auth.impl.AuthServiceImpl;
import by.tr.likeitnetwork.service.user.UserService;
import by.tr.likeitnetwork.service.user.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private AuthService authService = new AuthServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
