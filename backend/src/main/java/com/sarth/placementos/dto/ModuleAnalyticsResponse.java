package com.sarth.placementos.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ModuleAnalyticsResponse {

    private Long moduleId;

    private String moduleName;

    private Integer totalTasks;

    private Integer completedTasks;

    private Integer progressPercentage;
}