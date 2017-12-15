package by.tr.likeitnetwork.filter.auth.exception;

public class AuthFilterException extends Exception {
    public AuthFilterException() {
    }

    public AuthFilterException(String message) {
        super(message);
    }

    public AuthFilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthFilterException(Throwable cause) {
        super(cause);
    }
}
