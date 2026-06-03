package com.sarth.placementos.controller;

import com.sarth.placementos.dto.DashboardResponse;
import com.sarth.placementos.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/dashboard")
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    DashboardResponse get(
            @PathVariable Long userId
    ) {
        return service.getDashboard(userId);
    }
}