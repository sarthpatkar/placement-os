package com.sarth.placementos.dto;


import com.sarth.placementos.entity.Goal;
import com.sarth.placementos.enums.GoalType;
import lombok.Builder;


import java.time.LocalDate;


@Builder
public record GoalResponse(

        Long id,

        String title,

        String description,

        GoalType type,

        LocalDate targetDate

) {


    public static GoalResponse from(
            Goal goal
    ){

        return GoalResponse.builder()
                .id(goal.getId())
                .title(goal.getTitle())
                .description(goal.getDescription())
                .type(goal.getType())
                .targetDate(goal.getTargetDate())
                .build();
    }
}