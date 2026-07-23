package com.example.todo_app.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskUpdateInvalidArgumentException extends RuntimeException {

  public TaskUpdateInvalidArgumentException(String message) {
    super(message);
  }
}
