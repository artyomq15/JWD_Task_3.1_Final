package by.tr.likeitnetwork.service.user.impl;


import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.service.user.UserService;
import by.tr.likeitnetwork.service.util.Pagination;
import by.tr.likeitnetwork.service.validation.AuthValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Integer id) throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.findUserById(id);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public List<User> findUsersByNameOrLogin(String expression, int page, int countUser) throws UserServiceException {
        final String EXPRESSION_MARKER = "%";
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            int fromId = Pagination.countFromId(page, countUser);
            return userDAO.findUsersByNameOrLogin(EXPRESSION_MARKER + expression + EXPRESSION_MARKER, fromId, countUser);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public List<User> findBannedUsers(int page, int countUser) throws UserServiceException {
        return findUsersByBannedState(true, page, countUser);
    }

    @Override
    public List<User> findNotBannedUsers(int page, int countUser) throws UserServiceException {
        return findUsersByBannedState(false,page, countUser);
    }

    private List<User> findUsersByBannedState (boolean isBanned, int page, int countUser) throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            int fromId = Pagination.countFromId(page, countUser);
            return userDAO.findUsersByBannedState(isBanned, fromId, countUser);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public List<User> findAdmins(int page, int countUser) throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            int fromId = Pagination.countFromId(page, countUser);
            return userDAO.findAdmins(fromId, countUser);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public void banUser(int userId) throws UserServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().banUser(userId);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public void unbanUser(int userId) throws UserServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().unbanUser(userId);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public void setUserToAdmin(int userId) throws UserServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().setUserToAdmin(userId);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public void setAdminToUser(int userId) throws UserServiceException {
        try {
            DAOFactory.getInstance().getUserDAO().setAdminToUser(userId);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public boolean changePassword(int id, String oldPassword, String newPassword, String newPasswordConfirmation) throws UserServiceException {
        if (!AuthValidator.isValidPasswordsForChanging(oldPassword, newPassword, newPasswordConfirmation)) {
            return false;
        }
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            String passwordSalt = userDAO.checkOldPasswordMatchesPasswordInDataBase(id, oldPassword);
            return passwordSalt != null
                    && userDAO.updateNewPasswordInDataBase(id, newPassword, passwordSalt);
        } catch (UserDAOException ex) {
            throw new UserServiceException(ex);
        }
    }

    @Override
    public boolean changeProfileInfo(User user) throws UserServiceException {
        if (!AuthValidator.isValidProfileInfo(user.getName(), user.getEmail())) {
            return false;
        }
        try {
            return DAOFactory.getInstance().getUserDAO().changeProfileInfo(user);
        } catch (UserDAOException ex) {
            throw new UserServiceException("", ex);
        }
    }

    @Override
    public boolean changeImg(int id, String pathImg) throws UserServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().updateImg(id, pathImg);
        } catch (UserDAOException ex) {
            throw new UserServiceException("S", ex);
        }
    }
}
