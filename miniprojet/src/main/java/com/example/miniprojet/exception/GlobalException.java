package com.example.miniprojet.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.miniprojet.exception.model.ErrorForm;

@ControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorForm> userNotFoundHandler(UserNotFoundException exception)
    {
        ErrorForm error = ErrorForm.builder()
                            .timeStamp(LocalDateTime.now())
                            .message(exception.getMessage())
                            .httpStatus(HttpStatus.NOT_FOUND.value())
                            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorForm> eventNotFoundHandler(EventNotFoundException exception)
    {
        ErrorForm error = ErrorForm.builder()
                            .timeStamp(LocalDateTime.now())
                            .message(exception.getMessage())
                            .httpStatus(HttpStatus.NOT_FOUND.value())
                            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorForm> badRequestHandler(BadRequestException exception) {
        ErrorForm error = ErrorForm.builder()
                .timeStamp(LocalDateTime.now())
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorForm> runtimeExceptionHandler(RuntimeException exception) {
        ErrorForm error = ErrorForm.builder()
                .timeStamp(LocalDateTime.now())
                .message(exception.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN.value())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(error);
    }



}
