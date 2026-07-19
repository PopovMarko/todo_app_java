package com.example.todo_app.features.users.controller;

import com.example.todo_app.core.domain.*;
import java.util.List;

public interface UserService {

  UserDomain createUser(UserDomain user);

  List<UserDomain> getUsers();

  UserDomain getUser(int id);

  void deleteUser(int id);

  UserDomain updateUser(int id, UserDomain user);
}
