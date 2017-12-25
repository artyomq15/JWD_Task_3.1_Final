package by.tr.likeitnetwork.dao.topic.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.constant.DAOQuery;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.exception.ThemeDAOException;
import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.dao.topic.TopicDAO;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAOImpl implements TopicDAO{
    @Override
    public List<Topic> getAll(String localeLanguage) throws TopicDAOException {
        try(Connection connection = DataSource.getConnection()){
            CallableStatement getAll = connection.prepareCall(DAOQuery.SQL_CALL_GET_ALL_TOPICS);
            getAll.registerOutParameter(1, Types.INTEGER);
            getAll.registerOutParameter(2, Types.VARCHAR);
            getAll.registerOutParameter(3, Types.LONGVARCHAR);
            getAll.registerOutParameter(4, Types.TIMESTAMP);
            getAll.registerOutParameter(5, Types.INTEGER);
            getAll.registerOutParameter(6, Types.INTEGER);
            getAll.registerOutParameter(7, Types.VARCHAR);

            getAll.execute();
            List<Topic> topicList = new ArrayList<>();
            Topic topic;
            User user;

            ResultSet resultSet = getAll.getResultSet();
            while (resultSet.next()){
                topic = new Topic();
                topic.setId(resultSet.getInt(1));
                topic.setHeader(resultSet.getString(2));
                topic.setContext(resultSet.getString(3));
                topic.setCreatingDate(resultSet.getDate(4));

                topic.setTheme(DAOFactory.getInstance().getThemeDAO().getThemeById(localeLanguage, resultSet.getInt(5)));

                user = new User();
                user.setId(resultSet.getInt(6));
                user.setName(resultSet.getString(7));

                topic.setUser(user);
                topicList.add(topic);
            }
            return topicList;
        } catch (SQLException | DataSourceDAOException | ThemeDAOException ex) {
            throw new TopicDAOException(ex);
        }
    }

    @Override
    public boolean addTopic(Topic topic) throws TopicDAOException {
        try(Connection connection = DataSource.getConnection()){
            CallableStatement addTopic = connection.prepareCall(DAOQuery.SQL_CALL_ADD_TOPIC);
            addTopic.setString(1, topic.getHeader());
            addTopic.setString(2, topic.getContext());
            addTopic.setInt(3, topic.getUser().getId());
            addTopic.setInt(4, topic.getTheme().getId());

            return addTopic.executeUpdate() == 1;///////////////////
        } catch (SQLException | DataSourceDAOException ex) {
            throw new TopicDAOException(ex);
        }
    }
}
