package by.tr.likeitnetwork.dao.user;

import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.entity.User;

import java.util.List;

public interface UserDAO {
    User findUserById(int id) throws UserDAOException;
    List<User> findUsersByNameOrLogin (String expression, int fromId, int countUser) throws UserDAOException;
    List<User> findUsersByBannedState (boolean isBanned, int fromId, int countUser) throws UserDAOException;
    List<User> findAdmins (int fromId, int countUser) throws UserDAOException;
    void banUser (int userId) throws UserDAOException;
    void unbanUser (int userId) throws UserDAOException;
    void setUserToAdmin (int userId) throws UserDAOException;
    void setAdminToUser (int userId) throws UserDAOException;
    boolean changeProfileInfo (User user) throws UserDAOException;
    String checkOldPasswordMatchesPasswordInDataBase(int id, String oldPassword) throws UserDAOException;
    boolean updateNewPasswordInDataBase(int id, String password, String passwordSalt) throws UserDAOException;
}
