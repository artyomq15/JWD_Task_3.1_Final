package by.tr.likeitnetwork.dao.user.impl;


import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.tr.likeitnetwork.dao.constant.DBFieldName.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(String id) throws UserDAOException {
        User user = new User();
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DAOQuery.SQL_SELECT_ALL_USER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setName(resultSet.getString(NAME_USER));
                user.setSurname(resultSet.getString(SURNAME_USER));
                user.setRating(resultSet.getDouble(RATING_USER));
                user.setEmail(resultSet.getString(EMAIL_USER));
                user.setAbout(resultSet.getString(ABOUT_USER));
                user.setBanned(resultSet.getBoolean(IS_BANNED));
                return user;
            } else {
                return null;
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException(ex);
        }
    }
}
