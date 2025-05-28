package com.ruoyi.auth.web.session.exception;

public class InvalidSessionException extends SessionException{
    /**
     * Creates a new InvalidSessionException.
     */
    public InvalidSessionException() {
        super();
    }

    /**
     * Constructs a new InvalidSessionException.
     *
     * @param message the reason for the exception
     */
    public InvalidSessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidSessionException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidSessionException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }

}
