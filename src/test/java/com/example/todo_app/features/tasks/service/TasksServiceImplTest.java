package com.example.todo_app.features.tasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.todo_app.core.domain.TaskUpdatePatch;
import com.example.todo_app.core.domain.TasksDomain;
import com.example.todo_app.core.exception.TaskNotFoundException;
import com.example.todo_app.core.exception.TaskUpdateInvalidArgumentException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TasksServiceImplTest {

  @InjectMocks TasksServiceImpl taskServiceImpl;

  @Mock TasksRepository taskRepository;
  @Mock Validator createTaskValidator;

  TasksDomain mockTask =
      new TasksDomain(
          1, 1, "test title", "test description", false, LocalDateTime.now(), LocalDateTime.now());
  TasksDomain mockTaskInvalidTitle =
      new TasksDomain(
          1, 1, "t", "test description", false, LocalDateTime.now(), LocalDateTime.now());

  TaskUpdatePatch validPatch = new TaskUpdatePatch("test title", "test description", false);
  TaskUpdatePatch invalidTaskPatch = new TaskUpdatePatch(null, null, null);

  @Test
  public void cerateTaskRerurnsTask() {
    Mockito.when(taskRepository.createTask(mockTask)).thenReturn(mockTask);
    TasksDomain task = taskServiceImpl.createTask(mockTask);
    assertEquals(mockTask, task);
  }

  @Test
  public void createTaskReturnsError() {
    Mockito.when(createTaskValidator.validate(mockTaskInvalidTitle))
        .thenThrow(ConstraintViolationException.class);
    assertThrows(
        ConstraintViolationException.class, () -> taskServiceImpl.createTask(mockTaskInvalidTitle));
  }

  @Test
  public void getTasksReturnsList() {
    Mockito.when(taskRepository.getTasks(1)).thenReturn(List.of(mockTask));
    List<TasksDomain> taskList = taskServiceImpl.getTasks(1);
    assertEquals(List.of(mockTask), taskList);
  }

  @Test
  public void getTaskReturnsTask() {
    Mockito.when(taskRepository.getTask(1)).thenReturn(Optional.of(mockTask));
    TasksDomain task = taskServiceImpl.getTask(1);
    assertEquals(mockTask, task);
  }

  @Test
  public void getTaskReturnsError() {
    Mockito.when(taskRepository.getTask(1)).thenReturn(Optional.empty());
    assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.getTask(1));
  }

  @Test
  public void updateTaskReturnsTask() {
    Mockito.when(taskRepository.updateTask(1, validPatch)).thenReturn(Optional.of(mockTask));
    TasksDomain task = taskServiceImpl.updateTask(1, validPatch);
    assertEquals(mockTask, task);
  }

  @Test
  public void updateTaskReturnsErrNotFound() {
    Mockito.when(taskRepository.updateTask(1, validPatch)).thenReturn(Optional.empty());
    assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.updateTask(1, validPatch));
  }

  @Test
  public void updateTaskReturnsErrInvalidArgument() {
    assertThrows(
        TaskUpdateInvalidArgumentException.class,
        () -> taskServiceImpl.updateTask(1, invalidTaskPatch));
  }
}
