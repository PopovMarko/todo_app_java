package com.example.todo_app.features.users.repository.inmemory;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.core.exception.UserNotFoundException;
import com.example.todo_app.features.users.repository.Models.*;
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
    if (user == null) {
      throw new UserNotFoundException("user not found");
    }
    return user;
  }

  @Override
  public UserDomain createUser(UserDomain user) {
    UserModel userModel = UserModel.toModel(user, userId);
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
    UserModel userModel = userRepository.get(id);
    if (userModel == null) {
      throw new UserNotFoundException("user not found");
    }
    String name = userModel.name();
    String email = userModel.email();
    if (user.name() != null && !user.name().isBlank()) {
      name = user.name();
    }
    if (user.email() != null && !user.email().isBlank()) {
      email = user.email();
    }
    UserModel updatedUser =
        new UserModel(userModel.id(), name, email, userModel.createdAt(), LocalDateTime.now());
    userRepository.put(id, updatedUser);

    return updatedUser.toDomain();
  }
}
