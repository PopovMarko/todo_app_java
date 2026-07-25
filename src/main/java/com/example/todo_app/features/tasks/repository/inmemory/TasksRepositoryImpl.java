package com.example.todo_app.features.tasks.repository.inmemory;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.tasks.service.TasksRepository;
import java.util.List;
import java.util.Optional;

public class TasksRepositoryImpl implements TasksRepository {
  public List<TasksDomain> getTasks(int userId) {
    return null;
  }

  public Optional<TasksDomain> getTask(int taskId) {
    return null;
  }

  public TasksDomain createTask(TasksDomain task) {
    return null;
  }

  public void deleteTask(int taskId) {}

  public Optional<TasksDomain> updateTask(int taskId, TaskUpdatePatch patchTask) {
    return null;
  }
}
