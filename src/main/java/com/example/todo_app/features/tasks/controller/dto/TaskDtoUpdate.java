package com.example.todo_app.features.tasks.controller.dto;

import com.example.todo_app.core.domain.TaskUpdatePatch;

public record TaskDtoUpdate(String title, String description, Boolean completed) {
  public TaskUpdatePatch toPatch() {
    return new TaskUpdatePatch(title, description, completed);
  }
}
