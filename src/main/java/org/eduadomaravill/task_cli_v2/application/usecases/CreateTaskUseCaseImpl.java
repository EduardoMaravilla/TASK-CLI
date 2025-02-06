package org.eduadomaravill.task_cli_v2.application.usecases;

import org.eduadomaravill.task_cli_v2.application.port.input.ICreateTaskUseCase;
import org.eduadomaravill.task_cli_v2.application.port.output.ITaskRepositoryPort;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the {@link ICreateTaskUseCase} interface.
 * This class handles the use case for creating a task by interacting with the task repository.
 */
@Component
public class CreateTaskUseCaseImpl implements ICreateTaskUseCase {

    private final ITaskRepositoryPort taskRepositoryPort;

    /**
     * Constructs a new {@code CreateTaskUseCaseImpl} instance.
     * Initializes the {@link ITaskRepositoryPort} used to interact with the task repository.
     */
    @Autowired
    public CreateTaskUseCaseImpl(ITaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    /**
     * Creates a new task with the given description by using the task repository.
     *
     * @param descriptionTask The description of the task to create.
     * @return An {@code Optional} containing the created task if successful,
     *         or an empty {@code Optional} if the task could not be created.
     */
    @Override
    public Optional<Task> createTask(String descriptionTask) {
        return taskRepositoryPort.createTask(descriptionTask);
    }
}

