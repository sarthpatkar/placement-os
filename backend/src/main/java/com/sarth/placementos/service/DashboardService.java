package com.sarth.placementos.service;

import com.sarth.placementos.dto.DashboardResponse;
import com.sarth.placementos.dto.TaskResponse;
import com.sarth.placementos.dto.TrackResponse;
import com.sarth.placementos.repository.GoalRepository;
import com.sarth.placementos.repository.TaskRepository;
import com.sarth.placementos.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final GoalRepository goalRepository;
    private final TrackRepository trackRepository;
    private final TaskRepository taskRepository;
    private final DailyLogService dailyLogService;

    public DashboardResponse getDashboard(Long userId) {
        var log = dailyLogService.getToday(userId);

        long total = taskRepository.countUserTasks(userId);
        long completed = taskRepository.countCompletedTasks(userId);

        int percentage = total == 0
                ? 0
                : (int) ((completed * 100) / total);

        return DashboardResponse.builder()
                .activeGoals(
                        goalRepository.countByUserId(userId)
                )
                .todayFocusMinutes(
                        log.getTotalFocusMinutes()
                )
                .completedTodayTasks(
                        log.getCompletedTasks()
                )
                .totalTasks(
                        (int) total
                )
                .completedTasks(
                        (int) completed
                )
                .progressPercentage(
                        percentage
                )
                .activeTasks(
                        taskRepository
                                .findActiveTasks(userId)
                                .stream()
                                .map(TaskResponse::from)
                                .toList()
                )
                .activeTracks(
                        trackRepository
                                .findByUserIdAndArchivedFalse(userId)
                                .stream()
                                .map(TrackResponse::from)
                                .toList()
                )
                .build();
    }
}