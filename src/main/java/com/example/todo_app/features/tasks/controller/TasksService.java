package com.example.todo_app.features.tasks.controller;

import com.example.todo_app.core.domain.*;
import java.util.List;

public interface TasksService {
  List<TasksDomain> getTasks(int userId);

  TasksDomain createTask(TasksDomain task);

  TasksDomain getTask(int taskId);

  void deleteTask(int taskId);

  TasksDomain updateTask(int taskId, TaskUpdatePatch patchTask);
}
