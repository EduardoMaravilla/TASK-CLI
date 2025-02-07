package org.eduadomaravill.task_cli_v2.domain.model;

import lombok.Getter;
import org.eduadomaravill.task_cli_v2.domain.exception.InvalidStatusException;

@Getter // Generates the "Getter" methods for the fields.
public enum StatusTask {
    /**
     * The task has not been started yet.
     * This status is represented by the integer value 1 and the label "todo".
     */
    NOT_STARTED(1, "todo"),

    /**
     * The task is currently in progress.
     * This status is represented by the integer value 2 and the label "in-progress".
     */
    IN_PROGRESS(2, "in-progress"),

    /**
     * The task has been completed.
     * This status is represented by the integer value 3 and the label "done".
     */
    DONE(3, "done");

    private final int status;
    private final String label;

    /**
     * Constructs a StatusTask with the specified status code and label.
     *
     * @param status The integer value representing the status of the task.
     * @param label The string label associated with the status.
     */
    StatusTask(int status, String label) {
        this.status = status;
        this.label = label;
    }

    /**
     * Returns the StatusTask corresponding to the given status code.
     *
     * @param statusCode The status code to match.
     * @return The StatusTask corresponding to the status code.
     * @throws InvalidStatusException If no StatusTask matches the provided status code.
     */
    public static StatusTask fromStatusCode(int statusCode) {
        for (StatusTask status : StatusTask.values()) {
            if (status.getStatus() == statusCode) {
                return status;
            }
        }
        throw new InvalidStatusException("No StatusTask found for statusCode: " + statusCode);
    }

    public static StatusTask fromStatusLabel(String statusLabel) {
        for (StatusTask status : StatusTask.values()) {
            if (status.getLabel().equalsIgnoreCase(statusLabel)) {
                return status;
            }
        }
        throw new InvalidStatusException("No StatusTask found for statusLabel: " + statusLabel);
    }

}


