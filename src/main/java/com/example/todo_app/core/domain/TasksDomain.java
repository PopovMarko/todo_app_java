package com.example.todo_app.core.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TasksDomain {
  int id;
  int userId;

  @NotBlank(message = "title required")
  @Size(min = 3, max = 100)
  String title;

  @NotBlank(message = "description required")
  @Size(min = 1, max = 1000)
  String description;

  Boolean completed;

  LocalDateTime createdAt;

  LocalDateTime completedAt;
}
