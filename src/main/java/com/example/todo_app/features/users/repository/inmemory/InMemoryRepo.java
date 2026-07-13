package com.example.todo_app.features.users.repository.inmemory;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.users.repository.Models.*;
import com.example.todo_app.features.users.service.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRepo implements UserRepository {
  private int userId = 0;
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
    return userRepository.get(id).toDomain();
  }

  @Override
  public UserDomain createUser(UserDomain user) {
    UserModel userModel = UserModel.toModel(user, userId);
    userId++;
    userRepository.put(userModel.id(), userModel);
    return userModel.toDomain();
  }
}
