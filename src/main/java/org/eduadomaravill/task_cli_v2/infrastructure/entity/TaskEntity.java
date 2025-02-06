package org.eduadomaravill.task_cli_v2.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eduadomaravill.task_cli_v2.domain.model.StatusTask;
import org.eduadomaravill.task_cli_v2.domain.model.Task;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RegisterReflectionForBinding(TaskEntity.class)
public class TaskEntity implements Serializable {

    private Long idTaskEntity;

    private String descriptionTaskEntity;

    private int statusCode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TaskEntity taskEntityFromDomainModel(Task task){
        return new TaskEntity(task.getIdTask(),task.getDescriptionTask(),task.getStatusTask().getStatus(),task.getCreatedAt(),task.getUpdatedAt());
    }

    public Task toDomainModel(){
        return new Task(this.idTaskEntity, this.descriptionTaskEntity, StatusTask.fromStatusCode(this.statusCode), this.createdAt, this.updatedAt);
    }
}
