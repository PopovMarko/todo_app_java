package com.example.todo_app.features.tasks.service;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.core.exception.TaskNotFoundException;
import com.example.todo_app.features.tasks.controller.TasksService;
import jakarta.validation.Validator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {
  TasksRepository repository;
  Validator createTaskValidator;
  Validator updateTakValidator;

  public TasksServiceImpl(TasksRepository repository, Validator validator) {
    this.repository = repository;
    this.createTaskValidator = validator;
  }

  public List<TasksDomain> getTasks(int userId) {
    return repository.getTasks(userId);
  }

  public TasksDomain createTask(TasksDomain task) {
    createTaskValidator.validate(task);
    return repository.createTask(task);
  }

  public TasksDomain getTask(int taskId) {

    return repository
        .getTask(taskId)
        .orElseThrow(() -> new TaskNotFoundException("task not found"));
  }

  public void deleteTask(int taskId) {}

  public TasksDomain updateTask(int taskId, TaskUpdatePatch patchTask) {
    patchTask.validate();
    return repository
        .updateTask(taskId, patchTask)
        .orElseThrow(() -> new TaskNotFoundException("task not found"));
  }
}
