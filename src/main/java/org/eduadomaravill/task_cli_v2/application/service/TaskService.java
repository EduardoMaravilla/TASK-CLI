package org.eduadomaravill.task_cli_v2.application.service;


import org.eduadomaravill.task_cli_v2.application.port.input.ICreateTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.input.IDeleteTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.input.IRetrieveTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.input.IUpdateTaskUseCase;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that implements the use cases for managing tasks.
 * This class combines the functionality of creating, deleting, retrieving, and updating tasks
 * by delegating the operations to the corresponding use case implementations.
 */
@Service
public class TaskService implements ICreateTaskUseCase, IDeleteTaskUseCase, IRetrieveTaskUseCase, IUpdateTaskUseCase {

    private final ICreateTaskUseCase createTaskUseCase;
    private final IDeleteTaskUseCase deleteTaskUseCase;
    private final IRetrieveTaskUseCase retrieveTaskUseCase;
    private final IUpdateTaskUseCase updateTaskUseCase;

    /**
     * Constructs a new {@code TaskService} instance.
     * Initializes all use case implementations for task management.
     */
    @Autowired
    public TaskService(ICreateTaskUseCase createTaskUseCase, IDeleteTaskUseCase deleteTaskUseCase, IRetrieveTaskUseCase retrieveTaskUseCase, IUpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.retrieveTaskUseCase = retrieveTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    /**
     * Creates a new task with the provided description.
     *
     * @param descriptionTask The description of the task to create.
     * @return An {@code Optional} containing the created task if successful,
     * or an empty {@code Optional} if the task could not be created.
     */
    @Override
    public Optional<Task> createTask(String descriptionTask) {
        return createTaskUseCase.createTask(descriptionTask);
    }

    /**
     * Deletes a task identified by its unique ID.
     *
     * @param idTask The ID of the task to delete.
     * @return {@code true} if the task was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean deleteTask(Long idTask) {
        return deleteTaskUseCase.deleteTask(idTask);
    }

    /**
     * Retrieves a task by its unique ID.
     *
     * @param idTask The ID of the task to retrieve.
     * @return An {@code Optional} containing the task if found,
     * or an empty {@code Optional} if no task is found with the given ID.
     */
    @Override
    public Optional<Task> getTaskById(Long idTask) {
        return retrieveTaskUseCase.getTaskById(idTask);
    }

    /**
     * Retrieves all tasks in the system.
     *
     * @return A list of all tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        return retrieveTaskUseCase.getAllTasks();
    }

    /**
     * Updates the description of a task identified by its unique ID.
     *
     * @param idTask          The ID of the task to update.
     * @param descriptionTask The new description for the task.
     * @return An {@code Optional} containing the updated task if the update was successful,
     * or an empty {@code Optional} if the task could not be updated.
     */
    @Override
    public Optional<Task> updateTaskByDescription(Long idTask, String descriptionTask) {
        return updateTaskUseCase.updateTaskByDescription(idTask, descriptionTask);
    }

    @Override
    public Optional<Task> updateTaskByStatus(Long idTask, StatusTask statusTask) {
        return updateTaskUseCase.updateTaskByStatus(idTask, statusTask);
    }
}

