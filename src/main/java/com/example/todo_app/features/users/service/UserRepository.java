package com.example.todo_app.features.users.service;

import com.example.todo_app.core.domain.*;
import java.util.List;

public interface UserRepository {
  UserDomain createUser(UserDomain user);

  List<UserDomain> getUsers();

  UserDomain getUser(int id);

  void deleteUser(int id);

  UserDomain updateUser(int id, UserDomain user);
}
