package com.example.todo_app.core.domain;

import com.example.todo_app.core.exception.TaskUpdateInvalidArgumentException;

public record TaskUpdatePatch(String title, String description, Boolean completed) {

  public void validate() {

    if (title() == null && description() == null && completed() == null) {
      throw new TaskUpdateInvalidArgumentException("at least one argument has to be set");
    }
  }
}
