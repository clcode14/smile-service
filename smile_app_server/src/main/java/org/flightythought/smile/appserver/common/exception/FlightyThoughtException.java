package org.flightythought.smile.appserver.common.exception;

public class FlightyThoughtException extends RuntimeException {

    public FlightyThoughtException() {
        super();
    }

    public FlightyThoughtException(String message) {
        super(message);
    }

    public FlightyThoughtException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightyThoughtException(Throwable cause) {
        super(cause);
    }

    protected FlightyThoughtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
