package by.tr.likeitnetwork.dao.user.impl;


import by.tr.likeitnetwork.dao.constant.ConstantVariable;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.dao.util.Encryptor;
import by.tr.likeitnetwork.entity.input.UserInput;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(int id) throws UserDAOException {
        User user = new User();
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_GET_USER_BY_ID);
            callableStatement.setInt(1, id);

            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.LONGVARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.registerOutParameter(7, Types.BIT);
            callableStatement.registerOutParameter(8, Types.VARCHAR);


            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setName(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setRating(resultSet.getInt(3));
                user.setAbout(resultSet.getString(4));
                user.setRole(User.Role.valueOf(resultSet.getString(5)));
                user.setBanned(resultSet.getBoolean(6));
                user.setImg(resultSet.getString(7));
                return user;
            } else {
                return null;
            }

        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Find user by id error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<User> findUsersByNameOrLogin(String expression, int fromId, int countUser) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getUsers = connection.prepareCall(DAOQuery.SQL_CALL_GET_USER_BY_NAME_OR_LOGIN);
            getUsers.setString(1, expression);
            getUsers.setInt(2, fromId);
            getUsers.setInt(3, countUser);
            getUsers.registerOutParameter(4, Types.INTEGER);
            getUsers.registerOutParameter(5, Types.VARCHAR);
            getUsers.registerOutParameter(6, Types.VARCHAR);
            getUsers.registerOutParameter(7, Types.VARCHAR);
            getUsers.registerOutParameter(8, Types.VARCHAR);
            getUsers.registerOutParameter(9, Types.BIT);

            ResultSet resultSet = getUsers.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAbout(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(User.Role.valueOf(resultSet.getString(5)));
                user.setBanned(resultSet.getBoolean(6));
                userList.add(user);
            }
            return userList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Find user by name or login error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<User> findUsersByBannedState(boolean isBanned, int fromId, int countUser) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getUsers = connection.prepareCall(DAOQuery.SQL_CALL_GET_USER_BY_BANNED_STATE);
            getUsers.setBoolean(1, isBanned);
            getUsers.setInt(2, fromId);
            getUsers.setInt(3, countUser);
            getUsers.registerOutParameter(4, Types.INTEGER);
            getUsers.registerOutParameter(5, Types.VARCHAR);
            getUsers.registerOutParameter(6, Types.VARCHAR);
            getUsers.registerOutParameter(7, Types.VARCHAR);
            getUsers.registerOutParameter(8, Types.VARCHAR);

            ResultSet resultSet = getUsers.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAbout(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(User.Role.valueOf(resultSet.getString(5)));
                user.setBanned(isBanned);
                userList.add(user);
            }
            return userList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Find user by banned state error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<User> findAdmins(int fromId, int countUser) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getUsers = connection.prepareCall(DAOQuery.SQL_CALL_GET_ADMINS);
            getUsers.setInt(1, fromId);
            getUsers.setInt(2, countUser);
            getUsers.registerOutParameter(3, Types.INTEGER);
            getUsers.registerOutParameter(4, Types.VARCHAR);
            getUsers.registerOutParameter(5, Types.VARCHAR);
            getUsers.registerOutParameter(6, Types.VARCHAR);
            getUsers.registerOutParameter(7, Types.VARCHAR);
            getUsers.registerOutParameter(8, Types.BIT);

            ResultSet resultSet = getUsers.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAbout(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(User.Role.valueOf(resultSet.getString(5)));
                user.setBanned(resultSet.getBoolean(6));

                userList.add(user);
            }
            return userList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Find admins error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void banUser(int userId) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_BAN_USER);
            callableStatement.setInt(1, userId);

            callableStatement.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Ban user error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void unbanUser(int userId) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_UNBAN_USER);
            callableStatement.setInt(1, userId);

            callableStatement.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Unban user error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void setUserToAdmin(int userId) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_SET_USER_TO_ADMIN);
            callableStatement.setInt(1, userId);

            callableStatement.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Set user to admin error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void setAdminToUser(int userId) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_SET_ADMIN_TO_USER);
            callableStatement.setInt(1, userId);

            callableStatement.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Set admin to user error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean changeProfileInfo(int id, UserInput input) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_CHANGE_PROFILE_INFO);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, input.getName());
            callableStatement.setString(3, input.getEmail());
            callableStatement.setString(4, input.getAboutUser());

            return callableStatement.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Change profile info error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public String checkOldPasswordMatchesPasswordInDataBase(int id, String oldPassword) throws UserDAOException {
        String passwordInDataBase;
        String passwordSalt;
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement call = connection.prepareCall(DAOQuery.SQL_CALL_GET_PASSWORD_AND_SALT_BY_ID);
            call.setInt(1, id);

            call.registerOutParameter(2, Types.VARCHAR);
            call.registerOutParameter(3, Types.VARCHAR);

            ResultSet resultSet = call.executeQuery();
            if (resultSet.next()) {
                passwordInDataBase = resultSet.getString(1);
                passwordSalt = resultSet.getString(2);
                if (Encryptor.getPasswordHashCode(oldPassword, passwordSalt).equals(passwordInDataBase)) {
                    return passwordSalt;
                }
            }
            return null;
        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new UserDAOException("Check old password matches password in database error.", ex);
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

            CallableStatement addUser = connection.prepareCall(DAOQuery.SQL_CALL_UPDATE_PASSWORD_BY_ID);
            addUser.setInt(1, id);
            addUser.setString(2, passwordHash);

            return addUser.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;
        } catch (SQLException | DataSourceDAOException | NoSuchAlgorithmException ex) {
            throw new UserDAOException("Update new password in database error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean updateImg(int id, String pathImg) throws UserDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            PreparedStatement update = connection.prepareCall(DAOQuery.SQL_CALL_UPDATE_IMG);
            update.setString(1, pathImg);
            update.setInt(2, id);

            return update.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new UserDAOException("Update img error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
