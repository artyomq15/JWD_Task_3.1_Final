package by.tr.likeitnetwork.service.exception;


public class LocaleServiceException extends ServiceException {
    public LocaleServiceException() {
    }

    public LocaleServiceException(String message) {
        super(message);
    }

    public LocaleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocaleServiceException(Throwable cause) {
        super(cause);
    }
}
