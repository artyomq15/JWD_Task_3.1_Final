package by.tr.likeitnetwork.dao;

import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.auth.impl.AuthDAOImpl;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.user.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private AuthDAO authDAO = new AuthDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AuthDAO getAuthDAO() {
        return authDAO;
    }
}
