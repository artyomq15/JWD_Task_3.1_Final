package by.tr.likeitnetwork.dao.exception;

public class TopicDAOException extends DAOException{
    public TopicDAOException() {
    }

    public TopicDAOException(String message) {
        super(message);
    }

    public TopicDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicDAOException(Throwable cause) {
        super(cause);
    }
}
