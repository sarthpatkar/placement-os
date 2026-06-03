package com.sarth.placementos.controller;

import com.sarth.placementos.dto.TaskRequest;
import com.sarth.placementos.dto.TaskResponse;
import com.sarth.placementos.dto.TaskStatusUpdateRequest;
import com.sarth.placementos.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/modules/{moduleId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(
            @PathVariable Long moduleId,
            @Valid @RequestBody TaskRequest request) {
        return taskService.create(moduleId, request);
    }

    @GetMapping("/api/modules/{moduleId}/tasks")
    public List<TaskResponse> getAll(@PathVariable Long moduleId) {
        return taskService.getAllByModuleId(moduleId);
    }

    @PatchMapping("/api/tasks/{taskId}/status")
    public TaskResponse updateStatus(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskStatusUpdateRequest request) {
        return taskService.updateStatus(taskId, request);
    }
}
