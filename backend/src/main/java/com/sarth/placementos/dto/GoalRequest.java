package com.sarth.placementos.dto;

import com.sarth.placementos.enums.GoalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class GoalRequest {


    @NotBlank
    private String title;


    private String description;


    @NotNull
    private GoalType type;


    private LocalDate targetDate;
}