package com.sarth.placementos.controller;

import com.sarth.placementos.dto.ModuleRequest;
import com.sarth.placementos.dto.ModuleResponse;
import com.sarth.placementos.service.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks/{trackId}/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleResponse create(
            @PathVariable Long trackId,
            @Valid @RequestBody ModuleRequest request) {
        return moduleService.create(trackId, request);
    }

    @GetMapping
    public List<ModuleResponse> getAll(@PathVariable Long trackId) {
        return moduleService.getAllByTrackId(trackId);
    }
}
