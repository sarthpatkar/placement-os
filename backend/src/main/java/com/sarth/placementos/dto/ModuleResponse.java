package com.sarth.placementos.dto;

import com.sarth.placementos.entity.Module;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ModuleResponse {

    private Long id;
    private String title;
    private Integer progress;
    private Long trackId;
    private LocalDateTime createdAt;

    public static ModuleResponse from(Module module) {
        return ModuleResponse.builder()
                .id(module.getId())
                .title(module.getTitle())
                .progress(module.getProgress())
                .trackId(module.getTrack().getId())
                .createdAt(module.getCreatedAt())
                .build();
    }
}
