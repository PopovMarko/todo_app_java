package com.example.todo_app.core.repository.models;

import com.example.todo_app.core.domain.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
  int id;
  String name;
  String email;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;

  // public UserModel(
  //     int id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
  //   this.id = id;
  //   this.name = name;
  //   this.email = email;
  //   this.createdAt = createdAt;
  //   this.updatedAt = updatedAt;
  // }
  //
  public static UserModel toModel(UserDomain user, int id) {

    return new UserModel(
        id, user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
  }

  public UserDomain toDomain() {
    return new UserDomain(id, name, email, createdAt, updatedAt);
  }
}
