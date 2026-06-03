package com.sarth.placementos.dto;

import com.sarth.placementos.entity.Track;
import com.sarth.placementos.enums.TrackType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TrackResponse {

    private Long id;
    private String name;
    private TrackType type;
    private Long userId;
    private LocalDateTime createdAt;

    public static TrackResponse from(Track track) {
        return TrackResponse.builder()
                .id(track.getId())
                .name(track.getName())
                .type(track.getType())
                .userId(track.getUser().getId())
                .createdAt(track.getCreatedAt())
                .build();
    }
}
