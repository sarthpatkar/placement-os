package com.sarth.placementos.service;

import com.sarth.placementos.dto.TaskRequest;
import com.sarth.placementos.dto.TaskResponse;
import com.sarth.placementos.dto.TaskStatusUpdateRequest;
import com.sarth.placementos.entity.Module;
import com.sarth.placementos.entity.Task;
import com.sarth.placementos.enums.Status;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.ModuleRepository;
import com.sarth.placementos.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModuleRepository moduleRepository;
    private final DailyLogService dailyLogService;

    @Transactional
    public TaskResponse create(Long moduleId, TaskRequest request) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + moduleId));

        Status status = request.getStatus() != null ? request.getStatus() : Status.NOT_STARTED;
        LocalDateTime completedAt = status == Status.COMPLETED ? LocalDateTime.now() : null;

        Task task = Task.builder()
                .title(request.getTitle())
                .status(status)
                .plannedMinutes(request.getPlannedMinutes())
                .actualMinutes(request.getActualMinutes())
                .module(module)
                .completedAt(completedAt)
                .build();

        Task saved = taskRepository.save(task);
        if (status == Status.COMPLETED) {
            Long userId = module.getTrack().getUser().getId();
            dailyLogService.incrementCompletedTasks(userId, LocalDate.now());
        }

        return TaskResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllByModuleId(Long moduleId) {
        if (!moduleRepository.existsById(moduleId)) {
            throw new ResourceNotFoundException("Module not found: " + moduleId);
        }

        return taskRepository.findByModuleId(moduleId).stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional
    public TaskResponse updateStatus(Long taskId, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));

        Status previousStatus = task.getStatus();
        Status newStatus = request.getStatus();

        task.setStatus(newStatus);

        if (newStatus == Status.COMPLETED && previousStatus != Status.COMPLETED) {
            task.setCompletedAt(LocalDateTime.now());
            Long userId = task.getModule().getTrack().getUser().getId();
            dailyLogService.incrementCompletedTasks(userId, LocalDate.now());
        }

        return TaskResponse.from(taskRepository.save(task));
    }
}
