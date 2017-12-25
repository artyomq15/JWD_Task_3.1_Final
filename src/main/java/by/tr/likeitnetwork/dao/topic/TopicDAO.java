package by.tr.likeitnetwork.dao.topic;

import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.entity.Topic;

import java.util.List;

public interface TopicDAO {
    List<Topic> getAll(String localeLanguage) throws TopicDAOException ;
    boolean addTopic(Topic topic) throws TopicDAOException;
}
