package com.sarth.placementos.dto;

import com.sarth.placementos.entity.StudySession;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StudySessionResponse {

    private Long id;
    private Long userId;
    private Long taskId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationMinutes;
    private LocalDateTime createdAt;

    public static StudySessionResponse from(StudySession session) {
        return StudySessionResponse.builder()
                .id(session.getId())
                .userId(session.getUser().getId())
                .taskId(session.getTask() != null ? session.getTask().getId() : null)
                .title(session.getTitle())
                .description(session.getDescription())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .durationMinutes(session.getDurationMinutes())
                .createdAt(session.getCreatedAt())
                .build();
    }
}
