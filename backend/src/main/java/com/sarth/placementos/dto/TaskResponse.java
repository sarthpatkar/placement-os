package com.sarth.placementos.dto;

import com.sarth.placementos.entity.Task;
import com.sarth.placementos.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private Status status;
    private Integer plannedMinutes;
    private Integer actualMinutes;
    private Long moduleId;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;

    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(task.getStatus())
                .plannedMinutes(task.getPlannedMinutes())
                .actualMinutes(task.getActualMinutes())
                .moduleId(task.getModule().getId())
                .completedAt(task.getCompletedAt())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
