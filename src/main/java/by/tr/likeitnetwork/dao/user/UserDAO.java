package by.tr.likeitnetwork.dao.user;

import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.entity.User;

public interface UserDAO {
    User findUserById (String id) throws UserDAOException;
}
