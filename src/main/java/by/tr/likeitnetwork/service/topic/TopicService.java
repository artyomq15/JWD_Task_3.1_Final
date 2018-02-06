package by.tr.likeitnetwork.service.topic;

import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.input.TopicInput;
import by.tr.likeitnetwork.service.exception.TopicServiceException;

import java.util.List;

public interface TopicService {
    List<Topic> getAll(String localeLanguage, int pageNumber, int countTopic) throws TopicServiceException;
    List<Topic> search(String expression, String localeLanguage, int pageNumber, int countTopic) throws TopicServiceException;
    boolean addTopic (int userId, TopicInput input) throws TopicServiceException;
    boolean deleteTopic (int id) throws TopicServiceException;
    Topic getTopicById (String localeLanguage, int id) throws TopicServiceException;
    List<Topic> getTopicsByThemeId (String localeLanguage, int themeId, int pageNumber, int countTopic) throws TopicServiceException;
    List<Topic> getTopicsByUserId (String localeLanguage, int userId) throws TopicServiceException;
    List<Topic> getTopicsWhichCommendedByUser (String localeLanguage, int userId) throws TopicServiceException;
    int countTopicsOfUser (int userId) throws TopicServiceException;
}
