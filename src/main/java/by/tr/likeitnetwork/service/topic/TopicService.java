package by.tr.likeitnetwork.service.topic;

import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.exception.TopicServiceException;

import java.util.List;

public interface TopicService {
    List<Topic> getAll(String localeLanguage) throws TopicServiceException;
    boolean addTopic (Topic topic) throws TopicServiceException;
    Topic getTopicById (String localeLanguage, int id) throws TopicServiceException;
    List<Topic> getTopicsByThemeId (String localeLanguage, int themeId) throws TopicServiceException;
    List<Topic> getTopicsByUserId (String localeLanguage, int userId) throws TopicServiceException;
}
