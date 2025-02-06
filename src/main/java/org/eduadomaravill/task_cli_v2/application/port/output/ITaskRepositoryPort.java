package org.eduadomaravill.task_cli_v2.application.port.output;

import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for task repository operations.
 * This interface defines the methods for interacting with the underlying data source
 * to perform CRUD operations on tasks.
 */
public interface ITaskRepositoryPort {

    /**
     * Creates a new task with the given description.
     *
     * @param descriptionTask The description of the task to create.
     * @return An {@code Optional} containing the created task if successful,
     * or an empty {@code Optional} if the task could not be created.
     */
    Optional<Task> createTask(String descriptionTask);

    /**
     * Deletes a task by its unique identifier.
     *
     * @param idTask The ID of the task to delete.
     * @return {@code true} if the task was successfully deleted, {@code false} otherwise.
     */
    boolean deleteTask(Long idTask);

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param idTask The ID of the task to retrieve.
     * @return An {@code Optional} containing the task if found,
     * or an empty {@code Optional} if no task is found with the given ID.
     */
    Optional<Task> getTaskById(Long idTask);

    /**
     * Retrieves all tasks.
     *
     * @return A list of all tasks.
     */
    List<Task> getAllTasks();

    /**
     * Updates the description of an existing task.
     *
     * @param idTask      The ID of the task to update.
     * @param description The new description for the task.
     * @return An {@code Optional} containing the updated task if the update was successful,
     * or an empty {@code Optional} if the task could not be updated.
     */
    Optional<Task> updateTaskByDescription(Long idTask, String description);

    Optional<Task> updateTaskByStatus(Long idTask, StatusTask statusTask);

}

