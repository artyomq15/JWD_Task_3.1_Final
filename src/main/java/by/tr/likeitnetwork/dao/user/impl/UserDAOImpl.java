package by.tr.likeitnetwork.dao.user.impl;


import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.dao.util.Encryptor;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static by.tr.likeitnetwork.dao.constant.DBFieldName.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(int id) throws UserDAOException {
        User user = new User();
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setName(resultSet.getString(USER_NAME));
                user.setRating(resultSet.getInt(USER_LIKES));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setAbout(resultSet.getString(USER_ABOUT));
                user.setBanned(resultSet.getBoolean(USER_IS_BANNED));
                return user;
            } else {
                return null;
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException(ex);// не забывай про собственные сообщения в исключениях
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean changeProfileInfo(User user) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_CHANGE_PROFILE_INFO);
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, user.getName());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getAbout());

            return callableStatement.executeUpdate()==1;
        }catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public String checkOldPasswordMatchesPasswordInDataBase(int id, String oldPassword) throws UserDAOException {
        String passwordInDataBase;
        String passwordSalt;
        Connection connection = null;
        try  {
            connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DAOQuery.SELECT_PASSWORD_AND_SALT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passwordInDataBase = resultSet.getString(USER_PASSWORD);
                passwordSalt = resultSet.getString(USER_PASSWORD_SALT);
                if (Encryptor.getPasswordHashCode(oldPassword, passwordSalt).equals(passwordInDataBase)) {
                    return passwordSalt;
                }
            }
            return null;
        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new UserDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean updateNewPasswordInDataBase(int id, String password, String passwordSalt) throws UserDAOException {
        String passwordHash;
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            passwordHash = Encryptor.getPasswordHashCode(password, passwordSalt);

            PreparedStatement addUser = connection.prepareStatement(DAOQuery.SQL_UPDATE_NEW_PASSWORD_BY_ID);
            addUser.setString(1, passwordHash);
            addUser.setInt(2, id);

            int rowsAdded = addUser.executeUpdate();

            return rowsAdded != 0;
        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new UserDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
