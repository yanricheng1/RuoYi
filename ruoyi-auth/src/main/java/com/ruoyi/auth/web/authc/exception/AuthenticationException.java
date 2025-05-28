package com.ruoyi.auth.web.authc.exception;


/**
 * General exception thrown due to an error during the Authentication process.
 *
 * @since 0.1
 */
public class AuthenticationException extends RuntimeException
{

    /**
     * Creates a new AuthenticationException.
     */
    public AuthenticationException() {
        super();
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param message the reason for the exception
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
