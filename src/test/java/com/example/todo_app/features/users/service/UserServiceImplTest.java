package com.example.todo_app.features.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import com.example.todo_app.core.domain.UserDomain;
import com.example.todo_app.core.exception.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks UserServiceImpl userServiceImpl;

  @Mock UserRepository userRepository;

  UserDomain mockUser =
      new UserDomain(1, "vasia", "vasia@example.com", LocalDateTime.now(), LocalDateTime.now());
  UserDomain mockUserBadName =
      new UserDomain(1, "v", "vasia@example.com", LocalDateTime.now(), LocalDateTime.now());
  UserDomain mockUserBadEmail =
      new UserDomain(1, "v", "vasia.example.com", LocalDateTime.now(), LocalDateTime.now());
  UserDomain mockUserBadUpdate =
      new UserDomain(1, "", "  ", LocalDateTime.now(), LocalDateTime.now());
  List<UserDomain> mockUserList = new ArrayList<>(List.of(mockUser));

  @Test
  public void getUserReturnsExistedUser() {
    Mockito.when(userRepository.getUser(1)).thenReturn(mockUser);
    UserDomain user = userServiceImpl.getUser(1);
    assertEquals(mockUser.name(), user.name());
  }

  @Test
  public void getUserReturnsErrorWhenUserNotExist() {
    Mockito.when(userRepository.getUser(2)).thenThrow(new UserNotFoundException("user not found"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUser(2));
  }

  @Test
  public void getUsersReturnsListOfUsers() {
    Mockito.when(userRepository.getUsers()).thenReturn(mockUserList);
    List<UserDomain> userList = userServiceImpl.getUsers();
    assertEquals(mockUserList, userList);
  }

  @Test
  public void createUserReturnsSavedUser() {
    Mockito.when(userRepository.createUser(mockUser)).thenReturn(mockUser);
    UserDomain user = userServiceImpl.createUser(mockUser);
    assertEquals(mockUser, user);
  }

  @Test
  public void createUserReturnsErrorForExistedUser() {
    Mockito.when(userRepository.emailExists(anyString())).thenReturn(true);
    assertThrows(UserAlreadyExistsException.class, () -> userServiceImpl.createUser(mockUser));
  }

  @Test
  public void createUserReturnsErrorBadRequest() {
    assertThrows(IllegalArgumentException.class, () -> userServiceImpl.createUser(mockUserBadName));
    assertThrows(
        IllegalArgumentException.class, () -> userServiceImpl.createUser(mockUserBadEmail));
  }

  @Test
  public void updateUserReturnsUpdatedUser() {
    Mockito.when(userRepository.userExists(anyInt())).thenReturn(true);
    Mockito.when(userRepository.updateUser(mockUser.id(), mockUser)).thenReturn(mockUser);
    UserDomain user = userServiceImpl.updateUser(mockUser.id(), mockUser);
    assertEquals(mockUser, user);
  }

  @Test
  public void updateUserReturnsErrorBadPatch() {
    assertThrows(
        IllegalArgumentException.class,
        () -> userServiceImpl.updateUser(mockUserBadUpdate.id(), mockUserBadUpdate));
  }
}
