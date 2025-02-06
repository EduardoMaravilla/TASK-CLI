package org.eduadomaravill.task_cli_v2.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a task within the system.
 * Contains information about its status, description, and creation/update timestamps.
 */
@Getter //Generates the "Getter" methods of the fields.
@Setter //Generates the "Setter" methods of the fields.
@AllArgsConstructor //Generates the constructor with all the parameters.
@NoArgsConstructor //Generates an empty constructor.
public class Task {
    /**
     * Unique identifier of the task.
     */
    private Long idTask;

    /**
     * Detailed description of the task.
     */
    private String descriptionTask;

    /**
     * Current status of the task.
     */
    private StatusTask statusTask;

    /**
     * Date and time when the task was created.
     */
    private LocalDateTime createdAt;

    /**
     * Date and time of the last task update.
     */
    private LocalDateTime updatedAt;
}
