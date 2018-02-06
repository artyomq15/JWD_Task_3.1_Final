package by.tr.likeitnetwork.dao.auth;


import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.entity.input.UserInput;

public interface AuthDAO {
    boolean addUser(UserInput input)throws AuthDAOException;
    Integer getIdByLogin(String login) throws AuthDAOException;
    AuthToken getAuthTokensWhileSignIn(String login, String password) throws AuthDAOException;
    boolean refreshAuthTokens(int id, AuthToken tokens) throws AuthDAOException;
    boolean isAccessTokenRight(String token) throws AuthDAOException;
    boolean isRefreshTokenRight(String token) throws AuthDAOException;
}
