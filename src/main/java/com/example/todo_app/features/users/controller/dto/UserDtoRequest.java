package com.example.todo_app.features.users.controller.dto;

import com.example.todo_app.core.domain.UserDomain;
import java.time.LocalDateTime;

public record UserDtoRequest(String name, String email) {
  public UserDtoRequest {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is required");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("email is required");
    }
  }

  public UserDomain toDomain() {
    return new UserDomain(0, name, email, LocalDateTime.now(), LocalDateTime.now());
  }
}
