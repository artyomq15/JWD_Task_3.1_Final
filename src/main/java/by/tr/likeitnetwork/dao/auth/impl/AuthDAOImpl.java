package by.tr.likeitnetwork.dao.auth.impl;


import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.constant.ConstantVariable;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;
import by.tr.likeitnetwork.entity.input.UserInput;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AuthDAOImpl implements AuthDAO {

    @Override
    public boolean addUser(UserInput input) throws AuthDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement addUser = connection.prepareCall(DAOQuery.SQL_CALL_ADD_USER);
            addUser.setString(1, input.getName());
            addUser.setString(2, input.getEmail());
            addUser.setString(3, input.getLogin());

            String salt = Encryptor.generateSalt();

            addUser.setString(4, Encryptor.getPasswordHashCode(input.getPassword(), salt));
            addUser.setString(5, salt);


            return addUser.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;

        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new AuthDAOException("Add user error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public Integer getIdByLogin(String login) throws AuthDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callId = connection.prepareCall(DAOQuery.SQL_CALL_GET_USER_ID_BY_LOGIN);
            callId.setString(1, login);
            callId.registerOutParameter(2, Types.INTEGER);

            callId.execute();

            return callId.getInt(2);
        } catch (SQLException | DataSourceDAOException ex) {

            throw new AuthDAOException("Get id by login error", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }


    @Override
    public AuthToken getAuthTokensWhileSignIn(String login, String password) throws AuthDAOException {
        String passwordHash;
        String salt;
        int id;
        String role;
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_GET_INFO_FOR_SIGN_IN_BY_LOGIN);
            callableStatement.setString(1, login);

            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);

            ResultSet resultSet = callableStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            id = Integer.parseInt(resultSet.getString(1));
            passwordHash = resultSet.getString(2);
            salt = resultSet.getString(3);
            role = resultSet.getString(4);

            if (!Encryptor.getPasswordHashCode(password, salt).equals(passwordHash)) {
                return null;
            }

            String accessToken = Encryptor.generateAccessToken(id, role);
            String refreshToken = Encryptor.generateRefreshToken();

            return new AuthToken(accessToken, refreshToken);

        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new AuthDAOException("Get tokens for sign in error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean refreshAuthTokens(int id, AuthToken tokens) throws AuthDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_UPDATE_TOKENS_BY_ID);

            callableStatement.setString(1, tokens.getAccessToken());
            callableStatement.setString(2, tokens.getRefreshToken());
            callableStatement.setInt(3, id);

            return callableStatement.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;

        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException("Refresh tokens error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean isAccessTokenRight(String token) throws AuthDAOException {
        try {
            return isTokenRight(token, DAOQuery.SQL_CALL_GET_ID_BY_ACCESS_TOKEN);
        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException("Checking access token error.", ex);
        }
    }

    @Override
    public boolean isRefreshTokenRight(String token) throws AuthDAOException {
        try {
            return isTokenRight(token, DAOQuery.SQL_CALL_GET_ID_BY_REFRESH_TOKEN);
        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException("Checking refresh token error.", ex);
        }
    }

    private boolean isTokenRight(String token, String tokenTypeSQLQuery) throws SQLException, DataSourceDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getId = connection.prepareCall(tokenTypeSQLQuery);
            getId.setString(1, token);
            getId.registerOutParameter(2, Types.INTEGER);

            ResultSet resultSet = getId.executeQuery();
            return resultSet.next();
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
