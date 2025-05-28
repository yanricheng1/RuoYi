package com.ruoyi.auth.web.session.exception;

public class SessionException extends RuntimeException{
    public SessionException() {
        super();
    }

    /**
     * Constructs a new SessionException.
     *
     * @param message the reason for the exception
     */
    public SessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SessionException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public SessionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new SessionException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

}
