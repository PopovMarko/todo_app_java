package com.example.todo_app.core.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

  int id;
  String name;
  String email;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;

  public boolean valid() {
    if (this.getName().length() < 3 || this.getName().length() > 100) {
      return false;
    }
    return true;
  }
}
