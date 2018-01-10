package by.tr.likeitnetwork.service.exception;

public class MessageServiceException extends ServiceException {
    public MessageServiceException() {
    }

    public MessageServiceException(String message) {
        super(message);
    }

    public MessageServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageServiceException(Throwable cause) {
        super(cause);
    }
}
