# 🔥 Task CLI 🔥

## Overview
Task CLI is a command-line application powered by **Spring Boot 3.4.2** and **Spring Shell** that allows users to manage their tasks efficiently. It is also a challenge from [roadmap.sh](https://roadmap.sh/projects/task-tracker).

## Features
- Add, retrieve, update, delete tasks
- List tasks with filtering options
- Mark tasks as done or in progress
- Built-in Spring Shell commands

## Installation
Ensure you have **Java 17+** and **Maven** installed.

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/task-cli.git
   ```
2. Navigate to the project directory:
   ```sh
   cd task-cli
   ```
3. Build the project:
   ```sh
   mvn clean compile package -DskipTests
   ```
4. Run the application:
   ```sh
   java -jar target/task-cli-1.0.0.jar
   ```

## Available Commands
### Built-In Commands
```
task-cli:> help        # Display help about available commands
task-cli:> clear       # Clear the shell screen
task-cli:> exit        # Exit the shell
task-cli:> history     # Show command history
task-cli:> version     # Display version info
```

### Task Commands
```
task-cli:> add "Buy milk"            # Add a new task
task-cli:> get 2                     # Retrieve a task by ID
task-cli:> update 2 "Buy eggs"       # Update a task description
task-cli:> mark-done 4               # Mark a task as done
task-cli:> list                      # List all tasks
task-cli:> list done                 # List only completed tasks
task-cli:> list in-progress          # List tasks in progress
task-cli:> mark-in-progress 4        # Mark a task as in progress
task-cli:> delete 2                  # Delete a task
```

## Code Examples
### Greeting Command
```
task-cli:> greet
```
```


	🔥🔥🔥 ¡Hello, Welcome to Task Cli! 🔥🔥🔥



```
### Adding a Task
```
task-cli:> add "Buy Milk"
```

```
----------Task Added Successfully----------
===========================================
| ID          : 5                         |
| Description : Buy Milk                  |
| Status      : todo                      |
| Created At  : 02/10/2025 10:41:08 p. m. |
| Updated At  : 02/10/2025 10:41:08 p. m. |
===========================================
-------------------------------------------
```

### Listing Tasks
```
task-cli:> list
```
```
|---------------------------------------------------------Tasks List---------------------------------------------------------|
==============================================================================================================================
|| N°  | ID    | Description               | Status       | Created At                     | Updated At                     ||
==============================================================================================================================
|| 1   | 5     | Buy Milk                  | todo         | 02/10/2025 10:41:08 p. m.      | 02/10/2025 10:41:08 p. m.      ||
|| 2   | 6     | Run in the morning        | todo         | 02/10/2025 10:45:05 p. m.      | 02/10/2025 10:45:05 p. m.      ||
|| 3   | 7     | Hack the bank             | todo         | 02/10/2025 10:45:17 p. m.      | 02/10/2025 10:45:17 p. m.      ||
==============================================================================================================================
------------------------------------------------------------------------------------------------------------------------------
```
## Run binaries
1. Download the latest `task-cli-windows.zip` or `task-cli-linux.tar.gz` from [Releases](https://github.com/EduardoMaravilla/TASK-CLI/releases).

## License
This project is licensed under the [**MIT License**](LICENSE).

## Author
**EDUARDO MARAVILLA** - [GitHub Profile](https://github.com/EduardoMaravilla)

