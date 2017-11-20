package by.tr.likeitnetwork.service.user.impl;


import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.service.user.UserService;

public class UserServiceImpl implements UserService{
    @Override
    public String findId(String login, String password) throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            return userDAO.findId(login, password);
        } catch (UserDAOException ex){
            throw new UserServiceException(ex);
        }

    }

    @Override
    public User findUserById(String id)throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            return userDAO.findUserById(id);
        } catch (UserDAOException ex){
            throw new UserServiceException(ex);
        }
    }
}
