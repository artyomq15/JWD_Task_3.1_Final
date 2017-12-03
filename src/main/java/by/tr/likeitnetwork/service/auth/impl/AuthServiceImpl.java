package by.tr.likeitnetwork.service.auth.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;
import by.tr.likeitnetwork.service.validation.Validator;

public class AuthServiceImpl implements AuthService {
    @Override
    public boolean signUp(RegistrationInfo info) throws AuthServiceException{
        if (!Validator.isValidRegistrationInfo(info)){
            return false;
        }
        AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
        try {
            return authDAO.addUser(info);
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }

    }

    @Override
    public AuthToken signIn(String login, String password) throws AuthServiceException {
        if (!Validator.isValidLoginInfo(login, password)){
            return null;
        }
        AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
        try{
            return authDAO.getAuthTokensWhileSignIn(login, password);
        } catch (AuthDAOException ex){
            throw new AuthServiceException(ex);
        }

    }
}
