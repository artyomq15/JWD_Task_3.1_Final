package by.tr.likeitnetwork.dao.auth;


import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;

public interface AuthDAO {
    boolean addUser(RegistrationInfo info)throws AuthDAOException;
    Integer getIdByLogin(String login) throws AuthDAOException;
    AuthToken getAuthTokensWhileSignIn(String login, String password) throws AuthDAOException;
    AuthToken refreshAuthTokens(int id, String role) throws AuthDAOException;
    boolean isAccessTokenRight(String token) throws AuthDAOException;
    boolean isRefreshTokenRight(String token) throws AuthDAOException;
}
