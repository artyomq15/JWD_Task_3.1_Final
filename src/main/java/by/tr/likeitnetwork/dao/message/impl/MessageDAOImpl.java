package by.tr.likeitnetwork.dao.message.impl;

import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.MessageDAOException;
import by.tr.likeitnetwork.dao.message.MessageDAO;
import by.tr.likeitnetwork.dao.util.StringParser;
import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.entity.User;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public List<Message> getMessagesByTopicId(String localeLanguage, int topicId) throws MessageDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getMessage = connection.prepareCall(DAOQuery.SQL_CALL_GET_MESSAGES_BY_TOPIC_ID);
            getMessage.setInt(1, topicId);

            getMessage.registerOutParameter(2, Types.INTEGER);
            getMessage.registerOutParameter(3, Types.LONGVARCHAR);
            getMessage.registerOutParameter(4, Types.TIMESTAMP);
            getMessage.registerOutParameter(5, Types.INTEGER);
            getMessage.registerOutParameter(6, Types.INTEGER);
            getMessage.registerOutParameter(7, Types.VARCHAR);
            getMessage.registerOutParameter(8, Types.LONGVARCHAR);

            getMessage.executeQuery();

            List<Message> messageList = new ArrayList<>();
            User user;
            Message message;
            ResultSet resultSet = getMessage.getResultSet();
            while (resultSet.next()) {
                message = new Message();
                message.setTopicId(topicId);
                message.setId(resultSet.getInt(1));
                message.setContext(resultSet.getString(2));

                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                message.setCreatingDate(dateFormat.format(resultSet.getTimestamp(3)));


                message.setLikes(resultSet.getInt(4));

                user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));
                message.setUser(user);
                message.setLikedUserId(StringParser.getIdListFromString(resultSet.getString(7)));
                messageList.add(message);
            }
            return messageList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Message> getMessagesByUserId(String localeLanguage, int userId) throws MessageDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement getMessage = connection.prepareCall(DAOQuery.SQL_CALL_GET_MESSAGES_BY_USER_ID);
            getMessage.setInt(1, userId);

            getMessage.registerOutParameter(2, Types.INTEGER);
            getMessage.registerOutParameter(3, Types.LONGVARCHAR);
            getMessage.registerOutParameter(4, Types.TIMESTAMP);
            getMessage.registerOutParameter(5, Types.INTEGER);
            getMessage.registerOutParameter(6, Types.INTEGER);
            getMessage.registerOutParameter(7, Types.LONGVARCHAR);


            getMessage.executeQuery();

            List<Message> messageList = new ArrayList<>();
            User user;
            Message message;
            ResultSet resultSet = getMessage.getResultSet();
            while (resultSet.next()) {

                message = new Message();
                message.setId(resultSet.getInt(1));
                message.setContext(resultSet.getString(2));

                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                message.setCreatingDate(dateFormat.format(resultSet.getTimestamp(3)));


                message.setLikes(resultSet.getInt(4));
                message.setTopicId(resultSet.getInt(5));

                user = new User();
                user.setId(userId);
                message.setUser(user);
                message.setLikedUserId(StringParser.getIdListFromString(resultSet.getString(6)));
                messageList.add(message);
            }
            return messageList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void addMessage(Message message) throws MessageDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement addMessage = connection.prepareCall(DAOQuery.SQL_CALL_ADD_MESSAGE);
            addMessage.setString(1, message.getContext());
            addMessage.setInt(2, message.getUser().getId());
            addMessage.setInt(3, message.getTopicId());

            addMessage.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void deleteMessage(int messageId) throws MessageDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SQL_CALL_DELETE_MESSAGE);
            callableStatement.setInt(1, messageId);

            callableStatement.executeUpdate();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }


    @Override
    public void likeMessage(int messageId, int userId) throws MessageDAOException {
        likeOrUnlikeMessage(messageId, userId, DAOQuery.SQL_CALL_LIKE_MESSAGE);
    }

    @Override
    public void unlikeMessage(int messageId, int userId) throws MessageDAOException {
        likeOrUnlikeMessage(messageId, userId, DAOQuery.SQL_CALL_UNLIKE_MESSAGE);
    }

    @Override
    public int countMessagesOfUser(int userId) throws MessageDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SCL_CALL_COUNT_MESSAGES_OF_USER);
            callableStatement.setInt(1, userId);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    private void likeOrUnlikeMessage(int messageId, int userId, String query) throws MessageDAOException {
        Connection connection = null;
        try {
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userId);
            callableStatement.setInt(2, messageId);

            callableStatement.executeQuery();
        } catch (SQLException | DataSourceDAOException ex) {
            throw new MessageDAOException(ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
