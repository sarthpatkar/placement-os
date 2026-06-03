package com.sarth.placementos.dto;

import com.sarth.placementos.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    private Status status;

    @Min(value = 1, message = "Planned minutes must be positive")
    private Integer plannedMinutes;

    @Min(value = 0, message = "Actual minutes cannot be negative")
    private Integer actualMinutes;
}
