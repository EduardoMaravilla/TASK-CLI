package org.eduadomaravill.task_cli_v2.application.port.input;


import org.eduadomaravill.task_cli_v2.domain.model.Task;

import java.util.Optional;

/**
 * Use case for creating a new task.
 * Implementations of this interface should define how a task is created.
 */
public interface ICreateTaskUseCase {

    /**
     * Creates a new task with the given description.
     *
     * @param descriptionTask The description of the task.
     * @return An {@code Optional} containing the created task if successful,
     *         or an empty {@code Optional} if the task could not be created.
     */
    Optional<Task> createTask(String descriptionTask);
}
