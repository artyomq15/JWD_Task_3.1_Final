package by.tr.likeitnetwork.service.topic.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.TopicDAOException;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.input.TopicInput;
import by.tr.likeitnetwork.service.exception.TopicServiceException;
import by.tr.likeitnetwork.service.topic.TopicService;
import by.tr.likeitnetwork.service.util.Pagination;
import by.tr.likeitnetwork.service.validation.ValidatorFactory;

import java.util.List;

public class TopicServiceImpl implements TopicService{
    @Override
    public List<Topic> getAll(String localeLanguage, int pageNumber, int countTopic) throws TopicServiceException {
        try {
            int fromIdTopic = Pagination.countFromId(pageNumber,countTopic);
            return DAOFactory.getInstance().getTopicDAO().getAll(localeLanguage, fromIdTopic, countTopic);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public List<Topic> search(String expression, String localeLanguage, int pageNumber, int countTopic) throws TopicServiceException {
        try {
            int fromIdTopic = Pagination.countFromId(pageNumber,countTopic);
            return DAOFactory.getInstance().getTopicDAO().search(expression, localeLanguage, fromIdTopic, countTopic);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public boolean addTopic(int userId, TopicInput input) throws TopicServiceException {
        if (!ValidatorFactory.getInstance().getTopicValidator().isValid(input)){
            return false;
        }
        try{
            return DAOFactory.getInstance().getTopicDAO().addTopic(userId, input);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }

    }

    @Override
    public boolean deleteTopic(int id) throws TopicServiceException {
        try{
            return DAOFactory.getInstance().getTopicDAO().deleteTopic(id);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public Topic getTopicById(String localeLanguage, int id) throws TopicServiceException {
        try{
            return DAOFactory.getInstance().getTopicDAO().getTopicById(localeLanguage, id);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public List<Topic> getTopicsByThemeId(String localeLanguage, int themeId, int pageNumber, int countTopic) throws TopicServiceException {
        try{
            int fromIdTopic = Pagination.countFromId(pageNumber,countTopic);
            return DAOFactory.getInstance().getTopicDAO().getTopicsByThemeId(localeLanguage, themeId, fromIdTopic, countTopic);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public List<Topic> getTopicsByUserId(String localeLanguage, int userId) throws TopicServiceException {
        try{
            return DAOFactory.getInstance().getTopicDAO().getTopicsByUserId(localeLanguage, userId);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public List<Topic> getTopicsWhichCommendedByUser(String localeLanguage, int userId) throws TopicServiceException {
        try{
            return DAOFactory.getInstance().getTopicDAO().getTopicsWhichCommendedByUser(localeLanguage, userId);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }

    @Override
    public int countTopicsOfUser(int userId) throws TopicServiceException {
        try{
            return DAOFactory.getInstance().getTopicDAO().countTopicsOfUser(userId);
        } catch (TopicDAOException ex) {
            throw new TopicServiceException(ex);
        }
    }


}
