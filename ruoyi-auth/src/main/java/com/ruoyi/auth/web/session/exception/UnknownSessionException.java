package com.ruoyi.auth.web.session.exception;

public class UnknownSessionException extends InvalidSessionException {

    /**
     * Creates a new UnknownSessionException.
     */
    public UnknownSessionException() {
        super();
    }

    /**
     * Constructs a new UnknownSessionException.
     *
     * @param message the reason for the exception
     */
    public UnknownSessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnknownSessionException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public UnknownSessionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new UnknownSessionException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public UnknownSessionException(String message, Throwable cause) {
        super(message, cause);
    }
}