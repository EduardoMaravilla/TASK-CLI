package org.eduadomaravill.task_cli_v2.application.port.input;


import org.eduadomaravill.task_cli_v2.domain.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Use case for retrieving tasks.
 * Implementations of this interface should define how tasks are retrieved.
 */
public interface IRetrieveTaskUseCase {

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param idTask The ID of the task to retrieve.
     * @return An {@code Optional} containing the task if found,
     *         or an empty {@code Optional} if no task is found with the given ID.
     */
    Optional<Task> getTaskById(Long idTask);

    /**
     * Retrieves all tasks.
     *
     * @return A list of all tasks.
     */
    List<Task> getAllTasks();
}
