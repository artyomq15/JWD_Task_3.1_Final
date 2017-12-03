package by.tr.likeitnetwork.service.auth;

import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.exception.AuthServiceException;

public interface AuthService {
    boolean signUp(RegistrationInfo info) throws AuthServiceException;
    AuthToken signIn(String login, String password) throws AuthServiceException;
}
