package by.tr.likeitnetwork.dao.exception;

public class MessageDAOException extends DAOException {
    public MessageDAOException() {
    }

    public MessageDAOException(String message) {
        super(message);
    }

    public MessageDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageDAOException(Throwable cause) {
        super(cause);
    }
}
