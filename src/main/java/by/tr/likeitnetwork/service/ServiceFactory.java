package by.tr.likeitnetwork.service;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.DAOException;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.auth.impl.AuthServiceImpl;
import by.tr.likeitnetwork.service.exception.ServiceException;
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

    public static void initService()throws ServiceException{
        try {
            DAOFactory.initDatasource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
