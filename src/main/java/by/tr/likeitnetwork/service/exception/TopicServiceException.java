package by.tr.likeitnetwork.service.exception;

public class TopicServiceException extends ServiceException {
    public TopicServiceException() {
    }

    public TopicServiceException(String message) {
        super(message);
    }

    public TopicServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicServiceException(Throwable cause) {
        super(cause);
    }
}
