package com.sarth.placementos.dto;

import com.sarth.placementos.enums.TrackType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackAnalyticsResponse {

    private Long trackId;

    private String trackName;

    private TrackType trackType;

    private Integer totalTasks;

    private Integer completedTasks;

    private Integer progressPercentage;

    private Integer totalFocusMinutes;

    private List<ModuleAnalyticsResponse> modules;
}