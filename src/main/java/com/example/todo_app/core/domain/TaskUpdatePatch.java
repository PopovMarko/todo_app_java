package com.example.todo_app.core.domain;

import com.example.todo_app.core.exception.TaskUpdateInvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdatePatch {
  String title;
  String description;
  Boolean completed;

  public void validate() {

    if (getTitle() == null && getDescription() == null && getCompleted() == null) {
      throw new TaskUpdateInvalidArgumentException("at least one argument has to be set");
    }
  }
}
