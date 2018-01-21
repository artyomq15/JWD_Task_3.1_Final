package by.tr.likeitnetwork.service.user;

import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import java.util.List;

public interface UserService {
    //List<User> findAll ();
    User findUserById (Integer id) throws UserServiceException;

    boolean changePassword (int id, String oldPassword, String newPassword, String newPasswordConfirmation) throws UserServiceException;
    boolean changeProfileInfo(User user) throws UserServiceException;
}
