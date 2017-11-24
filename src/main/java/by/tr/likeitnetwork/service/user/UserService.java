package by.tr.likeitnetwork.service.user;

import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import java.util.List;

public interface UserService {
    //List<User> findAll ();
    User findUserById (String id) throws UserServiceException;
    //void changeProfile(String id, User user);

}
