package com.example.todo_app.core.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHttpExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> errorResponse(MethodArgumentNotValidException err) {

    Map<String, String> errors = new HashMap<>();

    err.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              String fieldName = error.getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  ResponseEntity<Map<String, String>> errorResponse(TaskNotFoundException err) {
    Map<String, String> response = Map.of("error", err.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<Map<String, String>> errorResponse(ConstraintViolationException err) {
    Map<String, String> errors = new HashMap<>();
    err.getConstraintViolations()
        .forEach(
            violation -> {
              String errorPath = violation.getPropertyPath().toString();
              String errorMessage = violation.getMessage();
              errors.put(errorPath, errorMessage);
            });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(TaskUpdateInvalidArgumentException.class)
  ResponseEntity<Map<String, String>> errorResponse(TaskUpdateInvalidArgumentException err) {
    Map<String, String> errors = Map.of("error: ", err.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
