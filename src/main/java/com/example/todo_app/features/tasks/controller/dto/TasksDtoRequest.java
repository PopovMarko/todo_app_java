package com.example.todo_app.features.tasks.controller.dto;

import com.example.todo_app.core.domain.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record TasksDtoRequest(
    @Positive(message = "userID required") int userId,
    @NotBlank(message = "title required")
        @Size(min = 3, max = 100, message = "size of title from 3 to 100 char")
        String title,
    @NotBlank(message = "description required")
        @Size(min = 1, max = 1000, message = "size of description from 1 to 1000 char")
        String description) {

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
