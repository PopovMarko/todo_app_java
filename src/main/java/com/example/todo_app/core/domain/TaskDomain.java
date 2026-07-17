package com.example.todo_app.core.domain;

import java.time.LocalDateTime;

public record TaskDomain(
    int id,
    int userId,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
