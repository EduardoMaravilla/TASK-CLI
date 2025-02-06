package org.eduadomaravill.task_cli_v2.infrastructure.command;

import org.eduadomaravill.task_cli_v2.application.service.TaskService;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Command-line interface for managing tasks using Spring Shell.
 * This component provides commands for creating, retrieving, updating,
 * and deleting tasks, as well as listing all tasks and changing their statuses.
 */
@ShellComponent
public class TaskCommands {

    private final TaskService taskService;
    private final Terminal terminal;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    private static final String ERROR_MESSAGE = "Error: Extra arguments provided.";

    /**
     * Constructs a TaskCommands component.
     *
     * @param taskService The service responsible for task operations.
     * @param filePath    The file path where tasks are stored.
     */
    @Autowired
    public TaskCommands(TaskService taskService, @Value("${task-cli.save.task.path}") String filePath,Terminal terminal) {
        this.taskService = taskService;
        this.terminal = terminal;
        terminal.writer().println("-----------------------------------------🖋️¡WELCOME TASK CLI!🖋️-----------------------------------------");
    }

    /**
     * Displays a greeting message.
     *
     * @return A greeting message.
     */
    @ShellMethod(key = "greeting", value = "Gets the greeting")
    public String helloWorld() {
        return "Hello, Welcome to Task Cli!";
    }

    /**
     * Adds a new task with the specified description.
     *
     * @param arg  The task description.
     * @param args Additional arguments (should not be provided).
     * @return A message indicating whether the task was successfully added.
     */
    @ShellMethod(key = "add", value = "Add a new task,\t\tExample: add \"Buy milk\"")
    public String addTask(@ShellOption(defaultValue = "Empty task") String arg, String... args) {
        if (args != null && args.length > 0) {
            return errorExtraArgument();
        }
        Optional<Task> taskOptional = taskService.createTask(arg);
        return taskOptional.map(task -> "----Task Added Successfully----\n" + printTask(task) +
                "-------------------------------").orElse("Task could not be created.");
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param arg  The task ID.
     * @param args Additional arguments (should not be provided).
     * @return The task details if found, otherwise an error message.
     */
    @ShellMethod(key = "get", value = "Retrieve a task with ID,\tExample: get 2")
    public String getTask(@ShellOption(defaultValue = "0") Long arg, String... args) {
        if (args != null && args.length > 0) {
            return errorExtraArgument();
        }
        Optional<Task> taskOptional = taskService.getTaskById(arg);
        return taskOptional.map(task -> "----------Task Found-----------\n" + printTask(task) +
                "-------------------------------").orElse("Task not found.");
    }

    /**
     * Lists all tasks or filters them by status.
     *
     * @param status The status filter (e.g., "all", "done", "in-progress").
     * @return A formatted list of tasks.
     */
    @ShellMethod(key = "list", value = "List all tasks without or with status,\tExample: 'list' or 'list done' or 'list in-progress'")
    public String listTasks(@ShellOption(defaultValue = "all") String status) {
        int marginLength = 121; // Longitud total de la línea
        StatusTask statusTask = status.equalsIgnoreCase("all") ? null : StatusTask.fromStatusLabel(status);
        List<Task> tasks = taskService.getAllTasks();

        if (statusTask != null) {
            tasks = tasks.stream().filter(task -> task.getStatusTask().equals(statusTask)).toList();
        }

        // Centrar "Tasks List"
        String tasksListText = "Tasks List";
        int totalSpaces = marginLength - tasksListText.length() - 2;
        int leftSpaces = totalSpaces / 2;
        int rightSpaces = totalSpaces - leftSpaces;
        String centeredTasksList = "|" + "-".repeat(leftSpaces) + tasksListText + "-".repeat(rightSpaces) + "|";

        return tasks.isEmpty()
                ? "No tasks found."
                : centeredTasksList + "\n" + printTaskList(tasks, marginLength) + "-".repeat(marginLength);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param arg  The task ID.
     * @param args Additional arguments (should not be provided).
     * @return A message indicating whether the task was deleted.
     */
    @ShellMethod(key = "delete", value = "Delete a task with ID,\tExample: delete 2")
    public String deleteTask(@ShellOption(defaultValue = "0") Long arg, String... args) {
        if (args != null && args.length > 0) {
            return errorExtraArgument();
        }
        return taskService.deleteTask(arg) ? "----Task Deleted Successfully----" : "Task not found or could not be deleted.";
    }

    @ShellMethod(key = "update", value = "Update a task with ID,\tExample: update 2 \"Buy eggs\"")
    public String updateTask(@ShellOption(defaultValue = "0") Long arg, String description, String... args) {
        if (args != null && args.length > 1) {
            return errorExtraArgument();
        }
        Optional<Task> taskOptional = taskService.updateTaskByDescription(arg, description);

        return taskOptional.map(task -> "----Task Updated Successfully----\n" + printTask(task) +
                "-------------------------------").orElse("Task not found or could not be updated.");
    }

    /**
     * Marks a task as done.
     *
     * @param arg  The task ID.
     * @param args Additional arguments (should not be provided).
     * @return A message indicating whether the task was marked as done.
     */
    @ShellMethod(key = "mark-done", value = "Mark a task as done with ID,\tExample: mark-done 4")
    public String markAsDone(@ShellOption(defaultValue = "0") Long arg, String... args) {
        if (args != null && args.length > 0) {
            return errorExtraArgument();
        }
        Optional<Task> taskOptional = taskService.updateTaskByStatus(arg, StatusTask.DONE);
        return taskOptional.map(task -> "----Task Marked as Done Successfully----\n" + printTask(task) + "-------------------------------").orElse("Task not found or could not be updated.");
    }

    /**
     * Marks a task as in progress by its ID.
     *
     * @param arg  The ID of the task to be updated.
     * @param args Extra arguments (should be empty).
     * @return A message indicating whether the update was successful or not.
     */
    @ShellMethod(key = "mark-in-progress", value = "Mark a task as in progress with ID,\tExample: mark-in-progress 4")
    public String markAsInProgress(@ShellOption(defaultValue = "0") Long arg, String... args) {
        if (args != null && args.length > 0) {
            return errorExtraArgument();
        }
        Optional<Task> taskOptional = taskService.updateTaskByStatus(arg, StatusTask.IN_PROGRESS);
        return taskOptional.map(task -> "----Task Marked as In Progress Successfully----\n" + printTask(task) +
                "-------------------------------").orElse("Task not found or could not be marked as in progress.");
    }

    /**
     * Formats and prints a single task.
     *
     * @param task The task to be formatted.
     * @return A formatted string representing the task details.
     */
    private String printTask(Task task) {
        String formattedCreatedAt = task.getCreatedAt().format(FORMATTER);
        String formattedUpdatedAt = task.getUpdatedAt().format(FORMATTER);
        return "=====================================" + "\n" +
                String.format("| %-12s: %s", "ID", task.getIdTask()) +
                "\n" +
                String.format("| %-12s: %s", "Description", task.getDescriptionTask()) +
                "\n" +
                String.format("| %-12s: %s", "Status", task.getStatusTask().getLabel()) +
                "\n" +
                String.format("| %-12s: %s", "Created At", formattedCreatedAt) +
                "\n" +
                String.format("| %-12s: %s", "Updated At", formattedUpdatedAt) +
                "\n" +
                "=====================================" +
                "\n";
    }


    /**
     * Formats and prints a list of tasks with a given margin length.
     *
     * @param tasks        The list of tasks to be formatted.
     * @param marginLength The margin length for formatting.
     * @return A formatted string representing the list of tasks.
     */
    private String printTaskList(List<Task> tasks, int marginLength) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("=".repeat(marginLength));
        sb.append("\n");
        sb.append(String.format("|| %-3s | %-5s | %-20s | %-12s | %-30s | %-30s ||", "N°", "ID", "Description", "Status", "Created At", "Updated At"));
        sb.append("\n");
        sb.append("=".repeat(marginLength));
        sb.append("\n");
        for (Task task : tasks) {
            String formattedCreatedAt = task.getCreatedAt().format(FORMATTER);
            String formattedUpdatedAt = task.getUpdatedAt().format(FORMATTER);
            sb.append(String.format("|| %-3s | %-5d | %-20s | %-12s | %-30s | %-30s ||",
                    count,
                    task.getIdTask(),
                    task.getDescriptionTask().substring(0, Math.min(20, task.getDescriptionTask().length())),
                    task.getStatusTask().getLabel(),
                    formattedCreatedAt,
                    formattedUpdatedAt
            ));
            sb.append("\n");
            count++;
        }
        sb.append("=".repeat(marginLength));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Returns an error message for extra arguments.
     *
     * @return A predefined error message.
     */
    private String errorExtraArgument() {
        return ERROR_MESSAGE;
    }

}
