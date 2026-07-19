package com.example.todo_app.features.tasks.controller;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.tasks.controller.dto.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tasks")
public class TasksController {
  TasksService service;

  public TasksController(TasksService service) {
    this.service = service;
  }

  @PostMapping
  public TasksDtoResponse createTask(@RequestBody TasksDtoRequest taskDto) {
    TasksDomain task = service.createTask(taskDto.toDomain());
    return TasksDtoResponse.toDto(task);
  }

  @GetMapping("{id}")
  public List<TasksDtoResponse> getTasks(@PathVariable int userId) {
    List<TasksDomain> tasksList = service.getTasks(userId);
    return TasksDtoResponse.toDto(tasksList);
  }
}
