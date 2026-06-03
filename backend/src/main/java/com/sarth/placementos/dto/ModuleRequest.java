package com.sarth.placementos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRequest {

    @NotBlank
    private String title;

    @Min(0)
    @Max(100)
    private Integer progress;
}
