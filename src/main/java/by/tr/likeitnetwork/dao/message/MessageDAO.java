package by.tr.likeitnetwork.dao.message;

import by.tr.likeitnetwork.dao.exception.MessageDAOException;
import by.tr.likeitnetwork.entity.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> getMessagesByTopicId (String localeLanguage, int topicId) throws MessageDAOException;
    List<Message> getMessagesByUserId (String localeLanguage, int userId) throws MessageDAOException;
    void addMessage (Message message) throws MessageDAOException;
    void deleteMessage (int messageId) throws MessageDAOException;
    //void updateMessage (int messageId) throws MessageDAOException;
    void likeMessage (int messageId, int userId) throws MessageDAOException;
    void unlikeMessage (int messageId, int userId) throws MessageDAOException;
    int countMessagesOfUser (int userId) throws MessageDAOException;
}
