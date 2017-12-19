package by.tr.likeitnetwork.dao.user;

import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.entity.User;

public interface UserDAO {
    User findUserById(int id) throws UserDAOException;

    boolean changePassword(int id, String oldPassword, String newPassword) throws UserDAOException;
}
