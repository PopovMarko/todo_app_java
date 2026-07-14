package com.example.todo_app.features.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import com.example.todo_app.core.domain.UserDomain;
import com.example.todo_app.core.exception.*;
import java.time.LocalDateTime;
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
}
