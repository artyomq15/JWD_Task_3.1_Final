package by.tr.likeitnetwork.dao.topic.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.constant.ConstantVariable;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.dao.topic.TopicDAO;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.entity.input.TopicInput;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TopicDAOImpl implements TopicDAO{
    @Override
    public List<Topic> getAll(String localeLanguage, int fromIdTopic, int countTopic) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement getAll = connection.prepareCall(DAOQuery.SQL_CALL_GET_ALL_TOPICS);
            getAll.setString(1, localeLanguage);
            getAll.setInt(2, fromIdTopic);
            getAll.setInt(3, countTopic);
            getAll.registerOutParameter(4, Types.INTEGER);
            getAll.registerOutParameter(5, Types.VARCHAR);
            getAll.registerOutParameter(6, Types.LONGVARCHAR);
            getAll.registerOutParameter(7, Types.TIMESTAMP);
            getAll.registerOutParameter(8, Types.INTEGER);
            getAll.registerOutParameter(9, Types.VARCHAR);
            getAll.registerOutParameter(10, Types.INTEGER);
            getAll.registerOutParameter(11, Types.VARCHAR);
            getAll.registerOutParameter(12, Types.VARCHAR);

            getAll.execute();
            List<Topic> topicList = new ArrayList<>();
            Topic topic;
            User user;
            Theme theme;

            ResultSet resultSet = getAll.getResultSet();
            while (resultSet.next()){
                topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setHeader(resultSet.getString(2));
                topic.setContext(resultSet.getString(3));

                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                topic.setCreatingDate(dateFormat.format(resultSet.getTimestamp(4)));

                user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));


                theme = new Theme();
                theme.setId(resultSet.getInt(7));
                theme.setName(resultSet.getString(8));

                user.setImg(resultSet.getString(9));
                topic.setTheme(theme);
                topic.setUser(user);

                topicList.add(topic);
            }
            return topicList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Get all error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Topic> search(String expression, String localeLanguage, int fromIdTopic, int countTopic) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement search = connection.prepareCall(DAOQuery.SQL_CALL_SEARCH_TOPICS);
            search.setString(1, expression);
            search.setString(2, localeLanguage);
            search.setInt(3, fromIdTopic);
            search.setInt(4, countTopic);
            search.registerOutParameter(5, Types.INTEGER);
            search.registerOutParameter(6, Types.VARCHAR);
            search.registerOutParameter(7, Types.LONGVARCHAR);
            search.registerOutParameter(8, Types.TIMESTAMP);
            search.registerOutParameter(9, Types.INTEGER);
            search.registerOutParameter(10, Types.VARCHAR);
            search.registerOutParameter(11, Types.INTEGER);
            search.registerOutParameter(12, Types.VARCHAR);
            search.registerOutParameter(13, Types.VARCHAR);

            search.execute();
            List<Topic> topicList = new ArrayList<>();
            Topic topic;
            User user;
            Theme theme;

            ResultSet resultSet = search.getResultSet();
            while (resultSet.next()){
                topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setHeader(resultSet.getString(2));
                topic.setContext(resultSet.getString(3));

                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                topic.setCreatingDate(dateFormat.format(resultSet.getTimestamp(4)));

                user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));


                theme = new Theme();
                theme.setId(resultSet.getInt(7));
                theme.setName(resultSet.getString(8));

                user.setImg(resultSet.getString(9));
                topic.setTheme(theme);
                topic.setUser(user);

                topicList.add(topic);
            }
            return topicList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Search error", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean addTopic(int id, TopicInput input) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement addTopic = connection.prepareCall(DAOQuery.SQL_CALL_ADD_TOPIC);
            addTopic.setString(1, input.getHeader());
            addTopic.setString(2, input.getContext());
            addTopic.setInt(3, id);
            addTopic.setInt(4, input.getThemeId());
            return addTopic.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Add topic error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteTopic(int id) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement deleteTopic = connection.prepareCall(DAOQuery.SQL_CALL_DELETE_TOPIC);
            deleteTopic.setInt(1, id);
            return deleteTopic.executeUpdate() == ConstantVariable.SUCCESSFUL_UPDATE_ONE_ROW_VALUE;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Delete topic error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public Topic getTopicById(String localeLanguage, int id) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement getTopic = connection.prepareCall(DAOQuery.SQL_CALL_GET_TOPIC_BY_ID);
            getTopic.setInt(1, id);
            getTopic.setString(2, localeLanguage);

            getTopic.registerOutParameter(3, Types.VARCHAR);
            getTopic.registerOutParameter(4, Types.LONGVARCHAR);
            getTopic.registerOutParameter(5, Types.TIMESTAMP);
            getTopic.registerOutParameter(6, Types.INTEGER);
            getTopic.registerOutParameter(7, Types.VARCHAR);
            getTopic.registerOutParameter(8, Types.INTEGER);
            getTopic.registerOutParameter(9, Types.VARCHAR);
            getTopic.registerOutParameter(10, Types.VARCHAR);

            getTopic.executeQuery();

            Topic topic;
            User user;
            Theme theme;
            ResultSet resultSet = getTopic.getResultSet();
            if (resultSet.next()){
                topic = new Topic();
                topic.setId(id);
                topic.setHeader(resultSet.getString(1));
                topic.setContext(resultSet.getString(2));


                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                topic.setCreatingDate(dateFormat.format(resultSet.getTimestamp(3)));

                user = new User();
                user.setId(resultSet.getInt(4));
                user.setName(resultSet.getString(5));


                theme = new Theme();
                theme.setId(resultSet.getInt(6));
                theme.setName(resultSet.getString(7));

                user.setImg(resultSet.getString(8));

                topic.setTheme(theme);
                topic.setUser(user);

                return topic;
            }
            return null;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Get topic by id error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Topic> getTopicsByThemeId(String localeLanguage, int themeId, int fromId, int countTopic) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement getTopics = connection.prepareCall(DAOQuery.SQL_CALL_GET_TOPICS_BY_THEME_ID);
            getTopics.setInt(1, themeId);
            getTopics.setInt(2, fromId);
            getTopics.setInt(3, countTopic);
            getTopics.setString(4, localeLanguage);

            getTopics.registerOutParameter(5, Types.INTEGER);
            getTopics.registerOutParameter(6, Types.VARCHAR);
            getTopics.registerOutParameter(7, Types.LONGVARCHAR);
            getTopics.registerOutParameter(8, Types.TIMESTAMP);
            getTopics.registerOutParameter(9, Types.INTEGER);
            getTopics.registerOutParameter(10, Types.VARCHAR);
            getTopics.registerOutParameter(11, Types.VARCHAR);
            getTopics.registerOutParameter(12, Types.VARCHAR);

            List<Topic> topicList = new ArrayList<>();
            Topic topic;
            User user;
            Theme theme;

            ResultSet resultSet = getTopics.executeQuery();
            while (resultSet.next()){
                topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setHeader(resultSet.getString(2));
                topic.setContext(resultSet.getString(3));


                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                topic.setCreatingDate(dateFormat.format(resultSet.getTimestamp(4)));

                user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));

                theme = new Theme();
                theme.setId(themeId);
                theme.setName(resultSet.getString(7));

                user.setImg(resultSet.getString(8));

                topic.setUser(user);
                topic.setTheme(theme);
                topicList.add(topic);
            }
            return topicList;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Get topic by theme id error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Topic> getTopicsByUserId(String localeLanguage, int userId) throws TopicDAOException {
        try {
            return getTopicsByUserIdByQuery(localeLanguage, userId, DAOQuery.SQL_CALL_GET_TOPICS_BY_USER_ID);
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Get topics by user id error.", ex);
        }
    }

    @Override
    public List<Topic> getTopicsWhichCommendedByUser(String localeLanguage, int userId) throws TopicDAOException {
        try {
            return getTopicsByUserIdByQuery(localeLanguage, userId, DAOQuery.SQL_CALL_GET_TOPICS_WHICH_COMMENTED_BY_USER);
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Get topics which commended by user error.", ex);
        }
    }

    private List<Topic> getTopicsByUserIdByQuery(String localeLanguage, int userId, String query) throws SQLException, DataSourceDAOException{
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement getTopics = connection.prepareCall(query);
            getTopics.setInt(1, userId);
            getTopics.setString(2, localeLanguage);
            getTopics.registerOutParameter(3, Types.INTEGER);
            getTopics.registerOutParameter(4, Types.VARCHAR);
            getTopics.registerOutParameter(5, Types.LONGVARCHAR);
            getTopics.registerOutParameter(6, Types.TIMESTAMP);
            getTopics.registerOutParameter(7, Types.INTEGER);
            getTopics.registerOutParameter(8, Types.INTEGER);
            getTopics.registerOutParameter(9, Types.VARCHAR);
            getTopics.registerOutParameter(10, Types.VARCHAR);

            List<Topic> topicList = new ArrayList<>();
            Topic topic;
            User user;
            Theme theme;

            ResultSet resultSet = getTopics.executeQuery();
            while (resultSet.next()){
                topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setHeader(resultSet.getString(2));
                topic.setContext(resultSet.getString(3));


                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, new Locale(localeLanguage));
                topic.setCreatingDate(dateFormat.format(resultSet.getTimestamp(4)));

                user = new User();
                user.setId(resultSet.getInt(5));
                user.setName(resultSet.getString(6));

                theme = new Theme();
                theme.setId(resultSet.getInt(7));
                theme.setName(resultSet.getString(8));

                topic.setUser(user);
                topic.setTheme(theme);
                topicList.add(topic);
            }
            return topicList;
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public int countTopicsOfUser(int userId) throws TopicDAOException {
        Connection connection = null;
        try{
            connection = DataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(DAOQuery.SCL_CALL_COUNT_TOPICS_OF_USER);
            callableStatement.setInt(1, userId);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException("Count topics of user error.", ex);
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
