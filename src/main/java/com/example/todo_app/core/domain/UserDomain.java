package com.example.todo_app.core.domain;

import java.time.LocalDateTime;

public record UserDomain(
    int id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {}
