package com.sarth.placementos.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record DashboardResponse(

        long activeGoals,

        int todayFocusMinutes,

        int completedTodayTasks,

        int totalTasks,

        int completedTasks,

        int progressPercentage,

        List<TaskResponse> activeTasks,

        List<TrackResponse> activeTracks

) {}