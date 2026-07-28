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
      if (user.getEmail().equals(u.getEmail())) {
        throw new UserAlreadyExistsException("email allready exists");
      }
    }
    userId++;
    userRepository.put(userModel.getId(), userModel);
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
    String name =
        (user.getName() != null && !user.getName().isBlank()) ? user.getName() : existing.getName();
    String email =
        (user.getEmail() != null && !user.getEmail().isBlank())
            ? user.getEmail()
            : existing.getEmail();
    UserModel merged = new UserModel(id, name, email, existing.getCreatedAt(), LocalDateTime.now());
    userRepository.put(id, merged);
    return merged.toDomain();
  }

  @Override
  public boolean emailExists(String email) {
    for (UserModel u : userRepository.values()) {
      if (u.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean userExists(int id) {
    for (UserModel u : userRepository.values()) {
      if (u.getId() == (id)) {
        return true;
      }
    }
    return false;
  }
}
