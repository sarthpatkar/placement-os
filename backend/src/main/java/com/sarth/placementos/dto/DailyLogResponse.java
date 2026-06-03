package com.sarth.placementos.dto;

import com.sarth.placementos.entity.DailyLog;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class DailyLogResponse {

    private Long id;
    private Long userId;
    private LocalDate date;
    private Integer totalFocusMinutes;
    private Integer completedTasks;
    private LocalDateTime createdAt;

    public static DailyLogResponse from(DailyLog log) {
        return DailyLogResponse.builder()
                .id(log.getId())
                .userId(log.getUser().getId())
                .date(log.getDate())
                .totalFocusMinutes(log.getTotalFocusMinutes())
                .completedTasks(log.getCompletedTasks())
                .createdAt(log.getCreatedAt())
                .build();
    }

    public static DailyLogResponse empty(Long userId, LocalDate date) {
        return DailyLogResponse.builder()
                .userId(userId)
                .date(date)
                .totalFocusMinutes(0)
                .completedTasks(0)
                .build();
    }
}
