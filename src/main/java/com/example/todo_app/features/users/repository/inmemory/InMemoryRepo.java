package com.example.todo_app.features.users.repository.inmemory;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.core.exception.UserAlreadyExistsException;
import com.example.todo_app.core.exception.UserNotFoundException;
import com.example.todo_app.core.repository.models.UserModel;
import com.example.todo_app.features.users.service.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRepo implements UserRepository {
  private int userId = 1;
  Map<Integer, UserModel> userRepository = new HashMap<>();

  @Override
  public List<UserDomain> getUsers() {
    List<UserDomain> users = new ArrayList<>();
    for (UserModel u : userRepository.values()) {
      users.add(u.toDomain());
    }

    return users;
  }

  @Override
  public UserDomain getUser(int id) {

    UserDomain user = userRepository.get(id).toDomain();
    return user;
  }

  @Override
  public UserDomain createUser(UserDomain user) {
    UserModel userModel = UserModel.toModel(user, userId);
    for (UserModel u : userRepository.values()) {
      if (user.email().equals(u.email())) {
        throw new UserAlreadyExistsException("email allready exists");
      }
    }
    userId++;
    userRepository.put(userModel.id(), userModel);
    return userModel.toDomain();
  }

  @Override
  public void deleteUser(int id) {
    UserModel res = userRepository.remove(id);
    if (res == null) {
      throw new UserNotFoundException("user not found");
    }
  }

  @Override
  public UserDomain updateUser(int id, UserDomain user) {
    UserModel existing = userRepository.get(id);
    if (existing == null) {
      throw new UserNotFoundException("user not found");
    }
    String name = (user.name() != null && !user.name().isBlank()) ? user.name() : existing.name();
    String email =
        (user.email() != null && !user.email().isBlank()) ? user.email() : existing.email();
    UserModel merged = new UserModel(id, name, email, existing.createdAt(), LocalDateTime.now());
    userRepository.put(id, merged);
    return merged.toDomain();
  }

  @Override
  public boolean emailExists(String email) {
    for (UserModel u : userRepository.values()) {
      if (u.email().equals(email)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean userExists(int id) {
    for (UserModel u : userRepository.values()) {
      if (u.id() == (id)) {
        return true;
      }
    }
    return false;
  }
}
