package com.sarth.placementos.dto;

import com.sarth.placementos.enums.TrackType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackRequest {

@NotBlank(message = "Track name is required")
private String name;

@NotNull(message = "Track type is required")
private TrackType type;
}