package com.example.todo_app.features.users.service;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.users.controller.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  public UserDomain createUser(UserDomain user) {
    return repository.createUser(user);
  }

  public List<UserDomain> getUsers() {
    return repository.getUsers();
  }

  public UserDomain getUser(int id) {
    return repository.getUser(id);
  }
}
