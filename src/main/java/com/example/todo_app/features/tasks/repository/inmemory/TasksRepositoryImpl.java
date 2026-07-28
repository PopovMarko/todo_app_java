package com.example.todo_app.features.tasks.repository.inmemory;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.tasks.service.TasksRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TasksRepositoryImpl implements TasksRepository {
  public int currentId = 1;

  public List<TasksDomain> getTasks(int userId) {
    return null;
  }

  public Optional<TasksDomain> getTask(int taskId) {
    return null;
  }

  public TasksDomain createTask(TasksDomain task) {
    task.setId(currentId);
    currentId++;

    return null;
  }

  public void deleteTask(int taskId) {}

  public Optional<TasksDomain> updateTask(int taskId, TaskUpdatePatch patchTask) {
    return null;
  }
}
