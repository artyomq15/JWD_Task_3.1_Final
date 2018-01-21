package by.tr.likeitnetwork.controller.util.exception;

public class WrongTokensException extends Exception{
    public WrongTokensException() {
    }

    public WrongTokensException(String message) {
        super(message);
    }

    public WrongTokensException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTokensException(Throwable cause) {
        super(cause);
    }
}
