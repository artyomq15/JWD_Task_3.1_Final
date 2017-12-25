package by.tr.likeitnetwork.service.topic;

import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.exception.TopicServiceException;

import java.util.List;

public interface TopicService {
    List<Topic> getAll(String localeLanguage) throws TopicServiceException;
    boolean addTopic (Topic topic) throws TopicServiceException;
}
