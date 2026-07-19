package com.example.todo_app.features.users.controller.dto;

import com.example.todo_app.core.domain.UserDomain;

public record UserDtoUpdate(String name, String email) {
  public UserDtoUpdate {
    if (name == null && email == null) {
      throw new IllegalArgumentException("at least one parameter required");
    }
  }

  public UserDomain toDomain(int id) {
    return new UserDomain(id, name, email, null, null);
  }
}
