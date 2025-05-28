package com.ruoyi.auth.web.session.exception;

public class ExpiredSessionException extends StoppedSessionException {

    /**
     * Creates a new ExpiredSessionException.
     */
    public ExpiredSessionException() {
        super();
    }

    /**
     * Constructs a new ExpiredSessionException.
     *
     * @param message the reason for the exception
     */
    public ExpiredSessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExpiredSessionException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ExpiredSessionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ExpiredSessionException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ExpiredSessionException(String message, Throwable cause) {
        super(message, cause);
    }
}

