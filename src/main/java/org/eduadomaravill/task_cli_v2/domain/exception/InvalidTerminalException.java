package org.eduadomaravill.task_cli_v2.domain.exception;

public class InvalidTerminalException extends RuntimeException{
    public InvalidTerminalException() {
        super();
    }

    public InvalidTerminalException(String message) {
        super(message);
    }

    public InvalidTerminalException(String message, Throwable cause) {
        super(message, cause);
    }
}
