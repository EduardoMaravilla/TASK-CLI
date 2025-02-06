package org.eduadomaravill.task_cli_v2.domain.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidCommandException} with no detail message.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Constructs a new {@code InvalidCommandException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}

