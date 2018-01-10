package by.tr.likeitnetwork.service.message.impl;

import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.MessageDAOException;
import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.service.exception.MessageServiceException;
import by.tr.likeitnetwork.service.message.MessageService;

import java.util.List;


public class MessageServiceImpl implements MessageService {
    @Override
    public List<Message> getMessagesByTopicId(String localeLanguage, int topicId) throws MessageServiceException {
        try {
            return DAOFactory.getInstance().getMessageDAO().getMessagesByTopicId(localeLanguage, topicId);
        } catch (MessageDAOException ex) {
            throw new MessageServiceException(ex);
        }
    }

    @Override
    public List<Message> getMessagesByUserId(String localeLanguage, int userId) throws MessageServiceException {
        return null;
    }

    @Override
    public boolean addMessage(Message message) throws MessageServiceException {
        if (message.getUser().isBanned()){
            return false;
        }
        try {
            DAOFactory.getInstance().getMessageDAO().addMessage(message);
            return true;
        } catch (MessageDAOException ex) {
            throw new MessageServiceException(ex);
        }
    }

    @Override
    public void likeMessage(int messageId, int userId) throws MessageServiceException {
        try {
            DAOFactory.getInstance().getMessageDAO().likeMessage(messageId, userId);
        } catch (MessageDAOException ex) {
            throw new MessageServiceException(ex);
        }
    }

    @Override
    public void unlikeMessage(int messageId, int userId) throws MessageServiceException {
        try {
            DAOFactory.getInstance().getMessageDAO().unlikeMessage(messageId, userId);
        } catch (MessageDAOException ex) {
            throw new MessageServiceException(ex);
        }
    }
}
