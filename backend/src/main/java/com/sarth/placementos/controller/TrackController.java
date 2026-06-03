package com.sarth.placementos.controller;

import com.sarth.placementos.dto.TrackRequest;
import com.sarth.placementos.dto.TrackResponse;
import com.sarth.placementos.service.TrackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackResponse create(
            @PathVariable Long userId,
            @Valid @RequestBody TrackRequest request) {
        return trackService.create(userId, request);
    }

    @GetMapping
    public List<TrackResponse> getAll(@PathVariable Long userId) {
        return trackService.getAllByUserId(userId);
    }
}
