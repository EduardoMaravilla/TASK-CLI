package org.eduadomaravill.task_cli_v2.application.usecases;


import org.eduadomaravill.task_cli_v2.application.port.input.IUpdateTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.output.ITaskRepositoryPort;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the {@link IUpdateTaskUseCase} interface.
 * This class handles the use case for updating a task by interacting with the task repository.
 */
@Component
public class UpdateTaskUseCaseImpl implements IUpdateTaskUseCase {

    private final ITaskRepositoryPort taskRepositoryPort;

    /**
     * Constructs a new {@code UpdateTaskUseCaseImpl} instance.
     * Initializes the {@link ITaskRepositoryPort} used to interact with the task repository.
     */
    public UpdateTaskUseCaseImpl(ITaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Updates the description of a task identified by its unique ID.
     *
     * @param idTask The ID of the task to update.
     * @param descriptionTask The new description for the task.
     * @return An {@code Optional} containing the updated task if the update was successful,
     *         or an empty {@code Optional} if the task could not be updated.
     */
    @Override
    public Optional<Task> updateTaskByDescription(Long idTask, String descriptionTask) {
        return taskRepositoryPort.updateTaskByDescription(idTask, descriptionTask);
    }

    @Override
    public Optional<Task> updateTaskByStatus(Long idTask, StatusTask statusTask) {
        return taskRepositoryPort.updateTaskByStatus(idTask,statusTask);
    }
}
