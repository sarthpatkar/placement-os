package com.sarth.placementos.exception;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ){

        return build(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                request
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){

        String message =
                ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();


        return build(
                message,
                HttpStatus.BAD_REQUEST,
                request
        );
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request
    ){

        return build(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }



    private ResponseEntity<ErrorResponse> build(
            String message,
            HttpStatus status,
            HttpServletRequest request
    ){

        return ResponseEntity
                .status(status)
                .body(

                ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(message)
                        .path(request.getRequestURI())
                        .build()

                );
    }
}