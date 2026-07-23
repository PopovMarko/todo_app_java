package com.example.todo_app.core.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record TasksDomain(
    int id,
    int userId,
    @NotBlank(message = "title required") @Size(min = 3, max = 100) String title,
    @NotBlank(message = "description required") @Size(min = 1, max = 1000) String description,
    boolean completed,
    LocalDateTime createdAt,
    LocalDateTime completedAt) {}
