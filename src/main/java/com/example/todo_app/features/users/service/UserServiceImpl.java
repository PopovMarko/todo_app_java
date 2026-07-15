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

  void validateUser(UserDomain user) {
    if (user.name() == null
        || user.name().isBlank()
        || user.name().length() < 3
        || user.name().length() > 100) {
      throw new IllegalArgumentException("name is required and must be between 3 and 100 characters");
    }
    if (user.email() == null || user.email().isBlank()) {
      throw new IllegalArgumentException("email is required");
    }
  }

  @Override
  public UserDomain createUser(UserDomain user) {
    if (repository.emailExists(user.email())) {
      throw new UserAlreadyExistsException("email allready in use");
    }
    if (!user.valid()) {
      throw new IllegalArgumentException("name length must be between 3 and 100 symbols");
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
    if (!repository.userExists(id)) {
      throw new UserNotFoundException("user not found");
    }
    repository.deleteUser(id);
  }

  @Override
  public UserDomain updateUser(int id, UserDomain user) {
    validateUser(user);
    if (!repository.userExists(id)) {
      throw new UserNotFoundException("user not found");
    }
    if (repository.emailExists(user.email())
        && !user.email().equals(repository.getUser(id).email())) {
      throw new UserAlreadyExistsException("email allready in use");
    }
    return repository.updateUser(id, user);
  }
}
