package org.eduadomaravill.task_cli_v2.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true) // Evita errores por propiedades faltantes
@RegisterReflectionForBinding(TaskEntity.class)
public class TaskEntity implements Serializable {

    private Long idTaskEntity;
    private String descriptionTaskEntity;
    private int statusCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonCreator
    public TaskEntity(
            @JsonProperty("idTaskEntity") Long idTaskEntity,
            @JsonProperty("descriptionTaskEntity") String descriptionTaskEntity,
            @JsonProperty("statusCode") int statusCode,
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("updatedAt") LocalDateTime updatedAt
    ) {
        this.idTaskEntity = idTaskEntity;
        this.descriptionTaskEntity = descriptionTaskEntity;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TaskEntity taskEntityFromDomainModel(Task task){
        return new TaskEntity(task.getIdTask(),task.getDescriptionTask(),task.getStatusTask().getStatus(),task.getCreatedAt(),task.getUpdatedAt());
    }

    public Task toDomainModel(){
        return new Task(this.idTaskEntity, this.descriptionTaskEntity, StatusTask.fromStatusCode(this.statusCode), this.createdAt, this.updatedAt);
    }
}
