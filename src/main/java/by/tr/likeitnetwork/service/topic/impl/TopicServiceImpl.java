package by.tr.likeitnetwork.service.topic.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.service.exception.TopicServiceException;
import by.tr.likeitnetwork.service.topic.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService{
    @Override
    public List<Topic> getAll(String localeLanguage) throws TopicServiceException {
        try {
            return DAOFactory.getInstance().getTopicDAO().getAll(localeLanguage);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public boolean addTopic(Topic topic) throws TopicServiceException {
        //validate
        try{
            return DAOFactory.getInstance().getTopicDAO().addTopic(topic);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }

    }
}
