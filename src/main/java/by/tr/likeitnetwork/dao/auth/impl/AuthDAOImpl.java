package by.tr.likeitnetwork.dao.auth.impl;


import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.RegistrationInfo;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static by.tr.likeitnetwork.dao.constant.DBFieldName.*;

public class AuthDAOImpl implements AuthDAO {

    @Override
    public boolean addUser(RegistrationInfo info) throws AuthDAOException {
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            PreparedStatement addUser = connection.prepareStatement(DAOQuery.SQL_INSERT_USER);
            addUser.setString(1, info.getName());
            addUser.setString(2, info.getEmail());
            addUser.setString(3, info.getLogin());

            String salt = Encryptor.generateSalt();

            addUser.setString(4, Encryptor.getPasswordHashCode(info.getPassword(), salt));
            addUser.setString(5, salt);
            int rowsUser = addUser.executeUpdate();

            return rowsUser != 0;

        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new AuthDAOException("INSERTING USER", ex);
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

            throw new AuthDAOException("GETTING", ex);
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
        try  {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_GET_INFO_FOR_SIGN_IN_BY_LOGIN);
            callableStatement.setString(1, login);

            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                passwordHash = resultSet.getString(USER_PASSWORD);
                salt = resultSet.getString(USER_PASSWORD_SALT);
                id = Integer.parseInt(resultSet.getString(USER_ID));
                role = resultSet.getString(USER_ROLE);
            } else {
                return null;
            }
            if (!Encryptor.getPasswordHashCode(password, salt).equals(passwordHash)) {
                return null;
            }
            return refreshAuthTokens(id, role);

        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new AuthDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public AuthToken refreshAuthTokens(int id, String role) throws AuthDAOException {
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_UPDATE_TOKENS_BY_ID);

            String accessToken = Encryptor.generateAccessToken(id, role);
            String refreshToken = Encryptor.generateRefreshToken();

            callableStatement.setString(1, accessToken);
            callableStatement.setString(2, refreshToken);
            callableStatement.setInt(3, id);

            callableStatement.executeUpdate();
            return new AuthToken(accessToken, refreshToken);
        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean isAccessTokenRight(String token) throws AuthDAOException {
        return isTokenRight(token, DAOQuery.SQL_SELECT_ACCESS_TOKEN_TRUE);
    }

    @Override
    public boolean isRefreshTokenRight(String token) throws AuthDAOException {
        return isTokenRight(token, DAOQuery.SQL_SELECT_REFRESH_TOKEN_TRUE);
    }

    private boolean isTokenRight(String token, String tokenTypeSQLQuery) throws AuthDAOException{
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(tokenTypeSQLQuery);
            preparedStatement.setString(1, token);
            ResultSet resultSet= preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
