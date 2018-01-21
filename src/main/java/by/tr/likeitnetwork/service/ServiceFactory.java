package by.tr.likeitnetwork.service;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.DAOException;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.auth.impl.AuthServiceImpl;
import by.tr.likeitnetwork.service.exception.ServiceException;
import by.tr.likeitnetwork.service.locale.LocaleService;
import by.tr.likeitnetwork.service.locale.impl.LocaleServiceImpl;
import by.tr.likeitnetwork.service.message.MessageService;
import by.tr.likeitnetwork.service.message.impl.MessageServiceImpl;
import by.tr.likeitnetwork.service.theme.ThemeService;
import by.tr.likeitnetwork.service.theme.impl.ThemeServiceImpl;
import by.tr.likeitnetwork.service.topic.TopicService;
import by.tr.likeitnetwork.service.topic.impl.TopicServiceImpl;
import by.tr.likeitnetwork.service.user.UserService;
import by.tr.likeitnetwork.service.user.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private AuthService authService = new AuthServiceImpl();
    private ThemeService themeService = new ThemeServiceImpl();
    private TopicService topicService = new TopicServiceImpl();
    private MessageService messageService = new MessageServiceImpl();
    private LocaleService localeService = new LocaleServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public static void initService() throws ServiceException {
        try {
            DAOFactory.initDatasource();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void destroyService() throws ServiceException {
        try {
            DAOFactory.destroyDatasource();
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

    public ThemeService getThemeService() {
        return themeService;
    }

    public TopicService getTopicService() {
        return topicService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public LocaleService getLocaleService() {
        return localeService;
    }
}
