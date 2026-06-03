package com.sarth.placementos.controller;

import com.sarth.placementos.dto.StudySessionRequest;
import com.sarth.placementos.dto.StudySessionResponse;
import com.sarth.placementos.service.StudySessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/sessions")
@RequiredArgsConstructor
public class StudySessionController {

    private final StudySessionService studySessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudySessionResponse create(
            @PathVariable Long userId,
            @Valid @RequestBody StudySessionRequest request) {
        return studySessionService.create(userId, request);
    }

    @GetMapping
    public List<StudySessionResponse> getAll(@PathVariable Long userId) {
        return studySessionService.getAllByUserId(userId);
    }

    @GetMapping("/today")
    public List<StudySessionResponse> getToday(@PathVariable Long userId) {
        return studySessionService.getTodayByUserId(userId);
    }
}
