package com.sarth.placementos.controller;

import com.sarth.placementos.dto.DailyLogResponse;
import com.sarth.placementos.service.DailyLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/daily-log")
@RequiredArgsConstructor
public class DailyLogController {

    private final DailyLogService dailyLogService;

    @GetMapping("/today")
    public DailyLogResponse getToday(@PathVariable Long userId) {
        return dailyLogService.getToday(userId);
    }
}
