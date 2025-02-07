package org.eduadomaravill.task_cli_v2.infrastructure.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.infrastructure.entity.TaskEntity;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Repository for managing tasks stored in a persistent JSON file.
 * Provides CRUD operations for handling tasks.
 */
@Component
public class PersistenceTaskRepository {

    private final Terminal terminal;
    private final String filePath;
    private static final String FILE_NAME = "tasks.json";
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new {@link PersistenceTaskRepository} with the specified dependencies.
     *
     * @param objectMapper Jackson object mapper for JSON serialization and deserialization.
     * @param filePath     Path to the directory where the tasks.json file will be stored.
     */
    @Autowired
    public PersistenceTaskRepository(ObjectMapper objectMapper, @Value("${task-cli.save.task.path}") String filePath, Terminal terminal) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;
        this.terminal = terminal;
    }

    /**
     * Creates and persists a new task.
     *
     * @param taskEntity The task entity to be created.
     * @return An {@link Optional} containing the created task, or {@link Optional#empty()} if saving failed.
     */
    public Optional<TaskEntity> createTask(TaskEntity taskEntity) {
        List<TaskEntity> tasks = new ArrayList<>(getAllTasks());
        long nextId = tasks.stream().mapToLong(TaskEntity::getIdTaskEntity).max().orElse(0) + 1;
        taskEntity.setIdTaskEntity(nextId);
        tasks.add(taskEntity);
        return saveToFile(tasks) ? Optional.of(taskEntity) : Optional.empty();
    }

    /**
     * Retrieves all tasks from storage.
     *
     * @return A list of {@link TaskEntity} objects, or an empty list if no tasks exist.
     */
    public List<TaskEntity> getAllTasks() {
        File file = new File(filePath, FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            return Collections.emptyList();
        }

        try {
            String content = Files.readString(file.toPath()).trim();

            if (content.isEmpty() || "[{}]".equals(content)) {
                return Collections.emptyList();
            }
            List<TaskEntity> tasks = objectMapper.readValue(file, new TypeReference<>() {});
            return tasks.stream()
                    .filter(task -> task.getIdTaskEntity() != null).toList();

        } catch (IOException e) {
            terminal.writer().println("Error reading: "+ e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param idTask The ID of the task to retrieve.
     * @return An {@link Optional} containing the task if found, otherwise empty.
     */
    public Optional<TaskEntity> getTaskById(long idTask) {
        return getAllTasks().stream().filter(task -> task.getIdTaskEntity() == idTask).findFirst();
    }

    /**
     * Updates a task's description.
     *
     * @param idTask         The ID of the task to update.
     * @param newDescription The new description for the task.
     * @return An {@link Optional} containing the updated task, or empty if the task was not found.
     */
    public Optional<TaskEntity> updateTaskByDescription(Long idTask, String newDescription) {
        return updateTask(idTask, task -> task.setDescriptionTaskEntity(newDescription));
    }

    /**
     * Updates a task's status.
     *
     * @param idTask     The ID of the task to update.
     * @param statusTask The new status of the task.
     * @return An {@link Optional} containing the updated task, or empty if the task was not found.
     */
    public Optional<TaskEntity> updateTaskByStatus(Long idTask, StatusTask statusTask) {
        return updateTask(idTask, task -> task.setStatusCode(statusTask.getStatus()));
    }

    /**
     * Deletes a task by its ID.
     *
     * @param idTask The ID of the task to delete.
     * @return {@code true} if the task was deleted successfully, otherwise {@code false}.
     */
    public boolean deleteTask(Long idTask) {
        List<TaskEntity> tasks = getAllTasks().stream()
                .filter(task -> !Objects.equals(task.getIdTaskEntity(), idTask)).toList();
        return saveToFile(tasks);
    }

    /**
     * Saves the list of tasks back to the JSON file.
     *
     * @param tasks The list of tasks to save.
     * @return {@code true} if saving was successful, otherwise {@code false}.
     */
    private boolean saveToFile(List<TaskEntity> tasks) {
        File directory = new File(filePath);
        if (!directory.exists() && !directory.mkdirs()) {
            terminal.writer().println("Failed to create directory: " + directory.getPath());
            return false;
        }
        try {
            File file = new File(directory,FILE_NAME);
            objectMapper.writeValue(file, tasks);
            return true;
        } catch (IOException e) {
            terminal.writer().println("Failed to write: "+ e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Helper method to update a task and persist the changes.
     *
     * @param idTask    The ID of the task to update.
     * @param updater   A lambda function that applies changes to the task.
     * @return An {@link Optional} containing the updated task, or empty if not found.
     */
    private Optional<TaskEntity> updateTask(Long idTask, TaskUpdater updater) {
        List<TaskEntity> tasks = getAllTasks();
        for (TaskEntity task : tasks) {
            if (task.getIdTaskEntity().equals(idTask)) {
                updater.update(task);
                task.setUpdatedAt(LocalDateTime.now());
                return saveToFile(tasks) ? Optional.of(task) : Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Functional interface for updating tasks.
     */
    @FunctionalInterface
    private interface TaskUpdater {
        void update(TaskEntity task);
    }
}
