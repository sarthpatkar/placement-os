package com.sarth.placementos.dto;

import com.sarth.placementos.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank
    private String title;

    private Status status;

    @Min(0)
    private Integer plannedMinutes;

    @Min(0)
    private Integer actualMinutes;
}
