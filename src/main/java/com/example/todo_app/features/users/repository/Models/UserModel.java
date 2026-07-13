package com.example.todo_app.features.users.repository.Models;

import com.example.todo_app.core.domain.*;
import java.time.LocalDateTime;

public record UserModel(
    int id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {

  public static UserModel toModel(UserDomain user, int id) {
    return new UserModel(id, user.name(), user.email(), user.createdAt(), user.updatedAt());
  }

  public UserDomain toDomain() {
    return new UserDomain(id, name, email, createdAt, updatedAt);
  }
}
