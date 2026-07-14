package com.example.todo_app.features.users.service;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.core.exception.*;
import com.example.todo_app.features.users.controller.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDomain createUser(UserDomain user) {
    if (repository.emailExists(user.email())) {
      throw new UserAlreadyExistsException("email allready in use");
    }
    return repository.createUser(user);
  }

  @Override
  public List<UserDomain> getUsers() {
    return repository.getUsers();
  }

  @Override
  public UserDomain getUser(int id) {
    return repository.getUser(id);
  }

  @Override
  public void deleteUser(int id) {
    repository.deleteUser(id);
  }

  @Override
  public UserDomain updateUser(int id, UserDomain user) {
    return repository.updateUser(id, user);
  }
}
