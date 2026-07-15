package com.example.todo_app.core.domain;

import java.time.LocalDateTime;

public record UserDomain(
    int id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {

  public boolean valid() {
    if (this.name().length() < 3 || this.name().length() > 100) {
      return false;
    }
    return true;
  }
}
