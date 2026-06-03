package com.sarth.placementos.validation;

import com.sarth.placementos.dto.StudySessionRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;
import java.time.LocalDateTime;

public class StudySessionValidator
        implements ConstraintValidator<ValidStudySession, StudySessionRequest> {


    @Override
    public boolean isValid(
            StudySessionRequest request,
            ConstraintValidatorContext context
    ) {

        if (
                request.getStartTime() == null ||
                request.getEndTime() == null
        ) {
            return true;
        }


        LocalDateTime start = request.getStartTime();
        LocalDateTime end = request.getEndTime();


        if (!end.isAfter(start)) {
            return false;
        }


        long minutes = Duration
                .between(start, end)
                .toMinutes();


        // maximum 12 hour session
        if (minutes > 720) {
            return false;
        }


        // no future logging
        if (start.isAfter(LocalDateTime.now())) {
            return false;
        }


        return true;
    }
}