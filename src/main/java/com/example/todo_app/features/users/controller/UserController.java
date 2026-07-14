package com.example.todo_app.features.users.controller;

import com.example.todo_app.core.domain.*;
import com.example.todo_app.features.users.controller.dto.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
  UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public UserDtoResponse createUser(@RequestBody UserDtoRequest userDto) {
    UserDomain newUser = service.createUser(userDto.toDomain());
    return UserDtoResponse.toDto(newUser);
  }

  @GetMapping("")
  public List<UserDtoResponse> getUsers() {
    List<UserDomain> users = service.getUsers();
    return UserDtoResponse.toDto(users);
  }

  @GetMapping("{id}")
  public UserDtoResponse getUser(@PathVariable int id) {
    UserDomain findedUser = service.getUser(id);
    return UserDtoResponse.toDto(findedUser);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable int id) {
    service.deleteUser(id);
  }

  @PutMapping("{id}")
  public UserDtoResponse updateUser(@PathVariable int id, @RequestBody UserDtoUpdate user) {
    return UserDtoResponse.toDto(service.updateUser(id, user.toDomain(id)));
  }
}
