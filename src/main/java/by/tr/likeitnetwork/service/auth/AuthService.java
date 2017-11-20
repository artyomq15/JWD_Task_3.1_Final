package by.tr.likeitnetwork.service.auth;

import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.exception.AuthServiceException;

public interface AuthService {
    boolean register(RegistrationInfo info) throws AuthServiceException;
}
