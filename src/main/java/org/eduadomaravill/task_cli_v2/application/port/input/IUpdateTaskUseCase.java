package org.eduadomaravill.task_cli_v2.application.port.input;


import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;

import java.util.Optional;

/**
 * Use case for updating a task.
 * Implementations of this interface should define how a task is updated.
 */
public interface IUpdateTaskUseCase {

    /**
     * Updates the description of a task identified by its ID.
     *
     * @param idTask The unique identifier of the task to update.
     * @param descriptionTask The new description for the task.
     * @return An {@code Optional} containing the updated task if the update was successful,
     *         or an empty {@code Optional} if the task was not found or could not be updated.
     */
    Optional<Task> updateTaskByDescription(Long idTask, String descriptionTask);

    Optional<Task> updateTaskByStatus(Long idTask, StatusTask statusTask);
}

