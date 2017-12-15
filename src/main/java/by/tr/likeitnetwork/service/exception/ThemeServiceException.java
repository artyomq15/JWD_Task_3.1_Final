package by.tr.likeitnetwork.service.exception;

public class ThemeServiceException extends ServiceException{
    public ThemeServiceException() {
    }

    public ThemeServiceException(String message) {
        super(message);
    }

    public ThemeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThemeServiceException(Throwable cause) {
        super(cause);
    }
}
