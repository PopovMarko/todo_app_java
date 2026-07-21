package com.example.todo_app.features.tasks.service;

import com.example.todo_app.core.domain.TasksDomain;
import java.util.List;
import java.util.Optional;

public interface TasksRepository {
  public List<TasksDomain> getTasks(int userId);

  public TasksDomain createTask(TasksDomain task);

  public Optional<TasksDomain> getTask(int taskId);

  public void deleteTask(int taskId);

  public Optional<TasksDomain> updateTask(int taskId, TasksDomain patchTask);
}
