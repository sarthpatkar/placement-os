package com.sarth.placementos.service;

import com.sarth.placementos.dto.TrackRequest;
import com.sarth.placementos.dto.TrackResponse;
import com.sarth.placementos.entity.Track;
import com.sarth.placementos.entity.User;
import com.sarth.placementos.exception.ResourceNotFoundException;
import com.sarth.placementos.repository.TrackRepository;
import com.sarth.placementos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    @Transactional
    public TrackResponse create(Long userId, TrackRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Track track = Track.builder()
                .name(request.getName())
                .type(request.getType())
                .user(user)
                .build();

        return TrackResponse.from(trackRepository.save(track));
    }

    @Transactional(readOnly = true)
    public List<TrackResponse> getAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }

        return trackRepository.findByUserId(userId).stream()
                .map(TrackResponse::from)
                .toList();
    }
}
