package by.tr.likeitnetwork.service.auth.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;

import java.security.MessageDigest;

public class AuthServiceImpl implements AuthService {
    @Override
    public boolean register(RegistrationInfo info) throws AuthServiceException{
        //validation

        String login = info.getLogin();
        AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
        try {
            return authDAO.isFreeLogin(login) && authDAO.register(info);
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }

    }
}
