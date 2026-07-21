package com.example.todo_app.features.tasks.controller;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.tasks.controller.dto.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tasks")
public class TasksController {
  TasksService service;

  @Autowired
  public TasksController(TasksService service) {
    this.service = service;
  }

  @PostMapping()
  public TasksDtoResponse createTask(@Valid @RequestBody TasksDtoRequest taskDto) {
    TasksDomain task = service.createTask(taskDto.toDomain());
    return TasksDtoResponse.toDto(task);
  }

  @GetMapping()
  public List<TasksDtoResponse> getTasks(@RequestParam int userId) {
    List<TasksDomain> tasksList = service.getTasks(userId);
    return TasksDtoResponse.toDto(tasksList);
  }

  @GetMapping("{taskId}")
  public TasksDtoResponse getTask(@PathVariable int taskId) {
    TasksDomain task = service.getTask(taskId);
    return TasksDtoResponse.toDto(task);
  }

  @DeleteMapping("{taskId}")
  public void deleteTask(@PathVariable int taskId) {
    service.deleteTask(taskId);
  }

  @PutMapping("{taskId}")
  public TasksDtoResponse updateTask(
      @PathVariable int taskId, @RequestBody TasksDtoRequest patchTask) {
    TasksDomain patchedTask = service.updateTask(taskId, patchTask.toDomain());
    return TasksDtoResponse.toDto(patchedTask);
  }
}
