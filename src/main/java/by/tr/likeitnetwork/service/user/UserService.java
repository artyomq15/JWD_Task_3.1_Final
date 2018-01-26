package by.tr.likeitnetwork.service.user;

import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import java.util.List;

public interface UserService {
    //List<User> findAll ();
    User findUserById (Integer id) throws UserServiceException;
    List<User> findUsersByNameOrLogin(String expression, int page, int countUser) throws UserServiceException;
    List<User> findBannedUsers (int page, int countUser) throws UserServiceException;
    List<User> findNotBannedUsers (int page, int countUser) throws UserServiceException;
    List<User> findAdmins (int page, int countUser) throws UserServiceException;
    void banUser (int userId) throws UserServiceException;
    void unbanUser (int userId) throws UserServiceException;
    void setUserToAdmin (int userId) throws UserServiceException;
    void setAdminToUser (int userId) throws UserServiceException;
    boolean changePassword (int id, String oldPassword, String newPassword, String newPasswordConfirmation) throws UserServiceException;
    boolean changeProfileInfo(User user) throws UserServiceException;
}
