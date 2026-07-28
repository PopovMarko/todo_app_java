package com.example.todo_app.features.tasks.controller.dto;

import com.example.todo_app.core.domain.TasksDomain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record TasksDtoResponse(
    int id,
    int userId,
    String title,
    String description,
    boolean completed,
    LocalDateTime createdAt,
    LocalDateTime completedAt) {

  public static TasksDtoResponse toDto(TasksDomain task) {
    return new TasksDtoResponse(
        task.getId(),
        task.getUserId(),
        task.getTitle(),
        task.getDescription(),
        task.getCompleted(),
        task.getCreatedAt(),
        task.getCompletedAt());
  }

  public static List<TasksDtoResponse> toDto(List<TasksDomain> tasksList) {
    List<TasksDtoResponse> tasksDtoList = new ArrayList<TasksDtoResponse>();
    for (TasksDomain t : tasksList) {
      tasksDtoList.add(TasksDtoResponse.toDto(t));
    }
    return tasksDtoList;
  }
}
