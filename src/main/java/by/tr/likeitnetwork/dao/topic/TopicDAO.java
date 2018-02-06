package by.tr.likeitnetwork.dao.topic;

import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.input.TopicInput;

import java.util.List;

public interface TopicDAO {
    List<Topic> getAll(String localeLanguage, int fromIdTopic, int countTopic) throws TopicDAOException;
    List<Topic> search(String expression, String localeLanguage, int fromIdTopic, int countTopic) throws TopicDAOException;
    boolean addTopic(int userId, TopicInput input) throws TopicDAOException;
    boolean deleteTopic (int id) throws TopicDAOException;
    Topic getTopicById (String localeLanguage, int id) throws TopicDAOException;
    List<Topic> getTopicsByThemeId (String localeLanguage, int themeId, int fromId, int countTopic) throws TopicDAOException;
    List<Topic> getTopicsByUserId (String localeLanguage, int userId) throws TopicDAOException;
    List<Topic> getTopicsWhichCommendedByUser (String localeLanguage, int userId) throws TopicDAOException;
    int countTopicsOfUser (int userId) throws TopicDAOException;
}
