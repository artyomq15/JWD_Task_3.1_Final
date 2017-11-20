package by.tr.likeitnetwork.dao.auth;


import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.entity.RegistrationInfo;

public interface AuthDAO {
    boolean isFreeLogin(String login) throws AuthDAOException;
    boolean register(RegistrationInfo info)throws AuthDAOException;
}
