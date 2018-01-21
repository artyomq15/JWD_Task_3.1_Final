package by.tr.likeitnetwork.service.message;

import by.tr.likeitnetwork.entity.Message;
import by.tr.likeitnetwork.service.exception.MessageServiceException;

import java.util.List;

public interface MessageService {
    List<Message> getMessagesByTopicId (String localeLanguage, int topicId) throws MessageServiceException;
    List<Message> getMessagesByUserId (String localeLanguage, int userId) throws MessageServiceException;
    boolean addMessage (Message message) throws MessageServiceException;
    void deleteMessage (int messageId) throws MessageServiceException;
    //void updateMessage (int messageId) throws MessageDAOException;
    void likeMessage (int messageId, int userId) throws MessageServiceException;
    void unlikeMessage (int messageId, int userId) throws MessageServiceException;
    int countMessagesOfUser (int userId) throws MessageServiceException;
}
