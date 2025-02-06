package org.eduadomaravill.task_cli_v2.application.usecases;


import org.eduadomaravill.task_cli_v2.application.port.input.IRetrieveTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.output.ITaskRepositoryPort;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link IRetrieveTaskUseCase} interface.
 * This class handles the use case for retrieving tasks by interacting with the task repository.
 */
@Component
public class RetrieveTaskUseCaseImpl implements IRetrieveTaskUseCase {

    private final ITaskRepositoryPort taskRepositoryPort;

    /**
     * Constructs a new {@code RetrieveTaskUseCaseImpl} instance.
     * Initializes the {@link ITaskRepositoryPort} used to interact with the task repository.
     */
    @Autowired
    public RetrieveTaskUseCaseImpl(ITaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Retrieves a task identified by its unique ID.
     *
     * @param idTask The ID of the task to retrieve.
     * @return An {@code Optional} containing the task if found,
     *         or an empty {@code Optional} if no task is found with the given ID.
     */
    @Override
    public Optional<Task> getTaskById(Long idTask) {
        return taskRepositoryPort.getTaskById(idTask);
    }

    /**
     * Retrieves all tasks in the system.
     *
     * @return A list of all tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.getAllTasks();
    }
}

