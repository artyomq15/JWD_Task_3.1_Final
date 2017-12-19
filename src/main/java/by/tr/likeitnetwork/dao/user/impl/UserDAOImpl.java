package by.tr.likeitnetwork.dao.user.impl;


import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.util.Encryptor;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.tr.likeitnetwork.dao.constant.DBFieldName.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(int id) throws UserDAOException {
        User user = new User();
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setName(resultSet.getString(USER_NAME));
                user.setRating(resultSet.getDouble(USER_LIKES));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setAbout(resultSet.getString(USER_ABOUT));
                user.setBanned(resultSet.getBoolean(USER_IS_BANNED));
                return user;
            } else {
                return null;
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException(ex);// не забывай про собственные сообщения в исключениях
        }
    }

    @Override
    public boolean changePassword(int id, String oldPassword, String newPassword) throws UserDAOException {
        String passwordSalt = checkOldPasswordMatchesPasswordInDataBase(id, oldPassword);
        if (passwordSalt == null) {
            return false;
        }
        return updateNewPasswordInDataBase(id, newPassword, passwordSalt);
    }

    private String checkOldPasswordMatchesPasswordInDataBase(int id, String oldPassword) throws UserDAOException {
        String passwordInDataBase;
        String passwordSalt;
        try (Connection connection = DataSource.getConnection()) {
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
        }
    }

    private boolean updateNewPasswordInDataBase(int id, String password, String passwordSalt) throws UserDAOException {
        String passwordHash;
        try (Connection connection = DataSource.getConnection()) {
            passwordHash = Encryptor.getPasswordHashCode(password, passwordSalt);

            PreparedStatement addUser = connection.prepareStatement(DAOQuery.SQL_UPDATE_NEW_PASSWORD_BY_ID);
            addUser.setString(1, passwordHash);
            addUser.setInt(2, id);

            int rowsAdded = addUser.executeUpdate();

            return rowsAdded != 0;
        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new UserDAOException(ex);
        }
    }
}
