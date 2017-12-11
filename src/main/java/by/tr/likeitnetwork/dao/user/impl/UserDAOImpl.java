package by.tr.likeitnetwork.dao.user.impl;


import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.entity.User;

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
}
