package by.tr.likeitnetwork.service.auth.impl;

import by.tr.likeitnetwork.controller.util.TokenParser;
import by.tr.likeitnetwork.controller.util.exception.InvalidTokenException;
import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;
import by.tr.likeitnetwork.service.validation.AuthValidator;

public class AuthServiceImpl implements AuthService {
    @Override
    public boolean signUp(RegistrationInfo info) throws AuthServiceException{
        final int VALUE_RETURNED_IF_LOGIN_IS_FREE = 0;

        if (!AuthValidator.isValidRegistrationInfo(info)){
            return false;
        }
        AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
        try {
            Integer id = authDAO.getIdByLogin(info.getLogin());
            return id == VALUE_RETURNED_IF_LOGIN_IS_FREE
                    && authDAO.addUser(info);
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }

    }

    @Override
    public AuthToken signIn(String login, String password) throws AuthServiceException {
        if (!AuthValidator.isValidSignInInfo(login, password)){
            return null;
        }
        AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
        try{
            AuthToken tokens = authDAO.getAuthTokensWhileSignIn(login, password);
            Integer id = TokenParser.parseId(tokens.getAccessToken());

            if (!authDAO.refreshAuthTokens(id, tokens)){
                return null;
            }
            return tokens;
        } catch (AuthDAOException ex){
            throw new AuthServiceException("Sign in error", ex);
        } catch (InvalidTokenException e) {
            throw new AuthServiceException("Invalid token in sign in", e);
        }
    }
    @Override
    public boolean checkAccessTokenIsRight(String token) throws AuthServiceException {
        try{
            return DAOFactory.getInstance().getAuthDAO().isAccessTokenRight(token);
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }
    }

    @Override
    public boolean checkRefreshTokenIsRight(String token) throws AuthServiceException {
        try{
            return DAOFactory.getInstance().getAuthDAO().isRefreshTokenRight(token);
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }
    }

    @Override
    public AuthToken refreshTokens(int id, String role) throws AuthServiceException {
        try{
            String accessToken = Encryptor.generateAccessToken(id, role);
            String refreshToken = Encryptor.generateRefreshToken();
            AuthToken tokens = new AuthToken(accessToken, refreshToken);

            if (!DAOFactory.getInstance().getAuthDAO().refreshAuthTokens(id, tokens)){
                return null;
            }

            return tokens;
        } catch (AuthDAOException ex) {
            throw new AuthServiceException(ex);
        }
    }


}
