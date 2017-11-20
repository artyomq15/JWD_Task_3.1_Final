package by.tr.likeitnetwork.dao.exception;

public class DataSourceDAOException extends DAOException{
    public DataSourceDAOException() {
    }

    public DataSourceDAOException(String message) {
        super(message);
    }

    public DataSourceDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceDAOException(Throwable cause) {
        super(cause);
    }
}
