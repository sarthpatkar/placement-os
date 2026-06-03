package com.sarth.placementos.dto;

import com.sarth.placementos.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateRequest {

    @NotNull
    private Status status;
}
