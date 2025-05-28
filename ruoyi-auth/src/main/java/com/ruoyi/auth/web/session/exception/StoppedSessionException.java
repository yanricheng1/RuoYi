package com.ruoyi.auth.web.session.exception;

public class StoppedSessionException  extends InvalidSessionException {
    /**
     * Creates a new StoppedSessionException.
     */
    public StoppedSessionException() {
        super();
    }

    /**
     * Constructs a new StoppedSessionException.
     *
     * @param message the reason for the exception
     */
    public StoppedSessionException(String message) {
        super(message);
    }

    /**
     * Constructs a new StoppedSessionException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public StoppedSessionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new StoppedSessionException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public StoppedSessionException(String message, Throwable cause) {
        super(message, cause);
    }

}
