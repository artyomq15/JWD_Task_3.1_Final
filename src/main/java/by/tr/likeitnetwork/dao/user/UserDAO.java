package by.tr.likeitnetwork.dao.user;

import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.entity.User;

public interface UserDAO {
    User findUserById(int id) throws UserDAOException;

    boolean changeProfileInfo (User user) throws UserDAOException;
    String checkOldPasswordMatchesPasswordInDataBase(int id, String oldPassword) throws UserDAOException;
    boolean updateNewPasswordInDataBase(int id, String password, String passwordSalt) throws UserDAOException;
}
