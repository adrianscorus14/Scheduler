package service;

public class NoTrainerAvailableException extends Exception {

    public NoTrainerAvailableException() {
    }

    public NoTrainerAvailableException(String message) {
        super(message);
    }

    public NoTrainerAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTrainerAvailableException(Throwable cause) {
        super(cause);
    }

    public NoTrainerAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
