package org.eduadomaravill.task_cli_v2.domain.exception;

/**
 * Exception thrown when an invalid status is encountered.
 */
public class InvalidStatusException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidStatusException} with no detail message.
     */
    public InvalidStatusException() {
        super();
    }

    /**
     * Constructs a new {@code InvalidStatusException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception.
     */
    public InvalidStatusException(String message) {
        super(message);
    }
}
