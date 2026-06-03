package com.sarth.placementos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRequest {

    @NotBlank(message = "Module title is required")
    private String title;

    @Min(value = 0, message = "Progress cannot be below 0")
    @Max(value = 100, message = "Progress cannot exceed 100")
    private Integer progress;
}