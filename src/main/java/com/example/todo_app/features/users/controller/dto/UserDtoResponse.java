package com.example.todo_app.features.users.controller.dto;

import com.example.todo_app.core.domain.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record UserDtoResponse(
    int id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
  public static UserDtoResponse toDto(UserDomain user) {
    return new UserDtoResponse(
        user.id(), user.name(), user.email(), user.createdAt(), user.updatedAt());
  }

  public static List<UserDtoResponse> toDto(List<UserDomain> users) {
    List<UserDtoResponse> usersDto = new ArrayList<>();
    for (UserDomain u : users) {
      usersDto.add(UserDtoResponse.toDto(u));
    }
    return usersDto;
  }
}
