package by.tr.likeitnetwork.dao.exception;

public class AuthDAOException extends DAOException{
    public AuthDAOException() {
    }

    public AuthDAOException(String message) {
        super(message);
    }

    public AuthDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthDAOException(Throwable cause) {
        super(cause);
    }
}
