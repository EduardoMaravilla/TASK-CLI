package org.eduadomaravill.task_cli_v2.infrastructure.repository;



import org.eduadomaravill.task_cli_v2.application.port.output.ITaskRepositoryPort;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.eduadomaravill.task_cli_v2.infrastructure.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Adapter class that implements {@link ITaskRepositoryPort} to interact with the persistence layer.
 * This adapter is responsible for converting domain model objects to persistence model objects
 * and delegating the actual CRUD operations to the {@link PersistenceTaskRepository}.
 */
@Component
public class TaskRepositoryPortAdapter implements ITaskRepositoryPort {

    private final PersistenceTaskRepository persistenceTaskRepository;

    /**
     * Constructs a new {@link TaskRepositoryPortAdapter} and initializes the {@link PersistenceTaskRepository}.
     */
    @Autowired
    public TaskRepositoryPortAdapter(PersistenceTaskRepository persistenceTaskRepository) {
        this.persistenceTaskRepository = persistenceTaskRepository;
    }

    /**
     * Creates a new task in the repository with the provided description.
     *
     * @param descriptionTask the description of the task to be created
     * @return an {@link Optional} containing the created {@link Task} if successful, or {@link Optional#empty()} if the task could not be created
     */
    @Override
    public Optional<Task> createTask(String descriptionTask) {
        Task task = new Task();
        task.setDescriptionTask(descriptionTask);
        task.setStatusTask(StatusTask.TODO);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Optional<TaskEntity> newTask = persistenceTaskRepository.createTask(TaskEntity.taskEntityFromDomainModel(task));
        return newTask.map(TaskEntity::toDomainModel);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param idTask the ID of the task to be deleted
     * @return {@code true} if the task was deleted successfully, or {@code false} if the task could not be found or deleted
     */
    @Override
    public boolean deleteTask(Long idTask) {
        return persistenceTaskRepository.deleteTask(idTask);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param idTask the ID of the task to be retrieved
     * @return an {@link Optional} containing the {@link Task} if found, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<Task> getTaskById(Long idTask) {
        return persistenceTaskRepository.getTaskById(idTask).map(TaskEntity::toDomainModel);
    }

    /**
     * Retrieves all tasks from the repository.
     *
     * @return a list of all {@link Task} objects
     */
    @Override
    public List<Task> getAllTasks() {
        return persistenceTaskRepository.getAllTasks().stream().map(TaskEntity::toDomainModel).toList();
    }

    /**
     * Updates the description of an existing task by its ID.
     *
     * @param idTask the ID of the task to be updated
     * @param description the new description for the task
     * @return an {@link Optional} containing the updated {@link Task} if successful, or {@link Optional#empty()} if the task could not be updated
     */
    @Override
    public Optional<Task> updateTaskByDescription(Long idTask, String description) {
        return persistenceTaskRepository.updateTaskByDescription(idTask, description).map(TaskEntity::toDomainModel);
    }

    @Override
    public Optional<Task> updateTaskByStatus(Long idTask, StatusTask statusTask) {
        return persistenceTaskRepository.updateTaskByStatus(idTask,statusTask).map(TaskEntity::toDomainModel);
    }
}