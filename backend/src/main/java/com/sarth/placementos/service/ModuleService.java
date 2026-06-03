package com.sarth.placementos.service;

import com.sarth.placementos.dto.ModuleRequest;
import com.sarth.placementos.dto.ModuleResponse;
import com.sarth.placementos.entity.Module;
import com.sarth.placementos.entity.Track;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.ModuleRepository;
import com.sarth.placementos.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final TrackRepository trackRepository;

    @Transactional
    public ModuleResponse create(Long trackId, ModuleRequest request) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new ResourceNotFoundException("Track not found: " + trackId));

        Module module = Module.builder()
                .title(request.getTitle())
                .progress(request.getProgress() != null ? request.getProgress() : 0)
                .track(track)
                .build();

        return ModuleResponse.from(moduleRepository.save(module));
    }

    @Transactional(readOnly = true)
    public List<ModuleResponse> getAllByTrackId(Long trackId) {
        if (!trackRepository.existsById(trackId)) {
            throw new ResourceNotFoundException("Track not found: " + trackId);
        }

        return moduleRepository.findByTrackId(trackId).stream()
                .map(ModuleResponse::from)
                .toList();
    }
}
