package domain;

public class MaximumNumberOfStudentsReachedException extends Exception {

    public MaximumNumberOfStudentsReachedException() {
    }

    public MaximumNumberOfStudentsReachedException(String message) {
        super(message);
    }

    public MaximumNumberOfStudentsReachedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaximumNumberOfStudentsReachedException(Throwable cause) {
        super(cause);
    }

    public MaximumNumberOfStudentsReachedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
