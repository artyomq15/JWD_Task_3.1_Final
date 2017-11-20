package by.tr.likeitnetwork.dao.auth.impl;


import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.AuthDAOException;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.RegistrationInfo;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImpl implements AuthDAO {
    @Override
    public boolean isFreeLogin(String login) throws AuthDAOException {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DAOQuery.GET_ACCOUNT_INFO);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new AuthDAOException(ex);
        }
    }

    @Override
    public boolean register(RegistrationInfo info) throws AuthDAOException {
        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);

            String id = Encryptor.generateRandomString();

            PreparedStatement addUser = connection.prepareStatement(DAOQuery.ADD_USER);
            addUser.setString(1, id);
            addUser.setString(2, info.getName());
            addUser.setString(3, info.getEmail());
            int rowsUser = addUser.executeUpdate();

            PreparedStatement addAccount = connection.prepareStatement(DAOQuery.ADD_ACCOUNT);
            String salt = Encryptor.generateRandomString();
            addAccount.setString(1, info.getLogin());
            addAccount.setString(2, Encryptor.getPasswordHashCode(info.getPassword(), salt));
            addAccount.setString(3, salt);
            addAccount.setString(4, id);
            int rowsAccount = addAccount.executeUpdate();

            if (rowsUser == 0 || rowsAccount == 0) {
                connection.rollback();
                return false;
            } else {
                connection.commit();
                return true;
            }


        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new AuthDAOException(ex);
        }
    }
}
