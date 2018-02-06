package by.tr.likeitnetwork.service.auth;

import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.exception.AuthServiceException;

public interface AuthService {
    boolean signUp(UserInput input) throws AuthServiceException;
    AuthToken signIn(UserInput input) throws AuthServiceException;
    boolean checkAccessTokenIsRight(String token) throws AuthServiceException;
    boolean checkRefreshTokenIsRight(String token) throws AuthServiceException;
    AuthToken refreshTokens(int id, String role) throws AuthServiceException;
}
