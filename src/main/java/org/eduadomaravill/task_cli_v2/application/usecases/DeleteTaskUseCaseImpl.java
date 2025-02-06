package org.eduadomaravill.task_cli_v2.application.usecases;


import org.eduadomaravill.task_cli_v2.application.port.input.IDeleteTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.output.ITaskRepositoryPort;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link IDeleteTaskUseCase} interface.
 * This class handles the use case for deleting a task by interacting with the task repository.
 */
@Component
public class DeleteTaskUseCaseImpl implements IDeleteTaskUseCase {

    private final ITaskRepositoryPort taskRepositoryPort;

    /**
     * Constructs a new {@code DeleteTaskUseCaseImpl} instance.
     * Initializes the {@link ITaskRepositoryPort} used to interact with the task repository.
     */
    public DeleteTaskUseCaseImpl(ITaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Deletes a task identified by its unique ID.
     *
     * @param idTask The ID of the task to be deleted.
     * @return {@code true} if the task was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean deleteTask(Long idTask) {
        return taskRepositoryPort.deleteTask(idTask);
    }
}

