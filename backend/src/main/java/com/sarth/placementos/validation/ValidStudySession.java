package com.sarth.placementos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudySessionValidator.class)
public @interface ValidStudySession {

    String message() default "Invalid study session time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}