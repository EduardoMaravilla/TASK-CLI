package org.eduadomaravill.task_cli_v2.application.port.input;

/**
 * Use case for deleting a task.
 * Implementations of this interface should define how a task is deleted.
 */
public interface IDeleteTaskUseCase {

    /**
     * Deletes a task by its unique identifier.
     *
     * @param idTask The ID of the task to be deleted.
     * @return {@code true} if the task was successfully deleted, {@code false} otherwise.
     */
    boolean deleteTask(Long idTask);
}

