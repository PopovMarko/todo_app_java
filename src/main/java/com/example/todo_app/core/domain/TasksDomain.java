package com.example.todo_app.core.domain;

import java.time.LocalDateTime;

public record TasksDomain(
    int id,
    int userId,
    String title,
    String description,
    boolean completed,
    LocalDateTime createdAt,
    LocalDateTime completedAt) {}
