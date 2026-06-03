package com.sarth.placementos.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAnalyticsResponse {

    private Integer totalFocusMinutes;

    private Integer totalCompletedTasks;

    private Integer currentStreak;

    private Integer longestStreak;

    private List<TrackAnalyticsResponse> tracks;

    private List<WeakAreaResponse> weakAreas;
}