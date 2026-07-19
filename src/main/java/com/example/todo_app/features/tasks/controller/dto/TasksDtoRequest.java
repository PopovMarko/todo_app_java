package com.example.todo_app.features.tasks.controller.dto;

import com.example.todo_app.core.domain.*;
import java.time.LocalDateTime;

public record TasksDtoRequest(int userId, String title, String description) {
  public TasksDtoRequest {
    if (userId() <= 0) {
      throw new IllegalArgumentException("invalid userId parameter");
    }
    if (title() == null || title().isBlank()) {
      throw new IllegalArgumentException("title field required");
    }
    if (description() == null || description().isBlank()) {
      throw new IllegalArgumentException("description field required");
    }
  }

  public TasksDomain toDomain() {
    LocalDateTime now = LocalDateTime.now();
    return new TasksDomain(0, userId, title, description, false, now, null);
  }
}
