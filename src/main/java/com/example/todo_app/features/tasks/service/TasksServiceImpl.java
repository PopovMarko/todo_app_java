package com.example.todo_app.features.tasks.service;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.tasks.controller.TasksService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TasksServiceImpl implements TasksService {
  TasksRepository repository;

  public TasksServiceImpl(TasksRepository repository) {
    this.repository = repository;
  }

  public List<TasksDomain> getTasks(int userId) {
    return null;
  }

  public TasksDomain createTask(TasksDomain task) {
    return null;
  }

  public TasksDomain getTask(int taskId) {

    return repository
        .getTask(taskId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
  }

  public void deleteTask(int taskId) {}

  public TasksDomain updateTask(int taskId, TasksDomain patchTask) {
    // need some validator!
    return repository
        .updateTask(taskId, patchTask)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
  }
}
