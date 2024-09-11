package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  User user1 = new User("John Doe", "john.doe@example.com", "password456", "johndoe");
  User user2 = new User("Jane Smith", "jane.smith@example.com", "password456", "janesmith");

  @Mock
  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  void setUp() {

    userService = new UserService(userRepository);

  }

  @Test
  @DisplayName("Should return all users without passwords")
  void getUsers() {

    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    List<UserDto> result = userService.getUsers();

    assertEquals(2, result.size(), "The result list should contain 2 users");

    assertEquals("John Doe", result.get(0).getFullName());
    assertEquals("john.doe@example.com", result.get(0).getEmail());

    assertEquals("Jane Smith", result.get(1).getFullName());
    assertEquals("jane.smith@example.com", result.get(1).getEmail());

    verify(userRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should successfully add a new user")
  void addUser() {

    when(userRepository.save(user1)).thenReturn(user1);

    UserCreationDto userCreationDto = userService.convertTo(user1, UserCreationDto.class);

    UserDto result = userService.addUser(userCreationDto);

    assertEquals(user1.getFullName(), result.getFullName());
    assertEquals(user1.getEmail(), result.getEmail());
    assertEquals(user1.getUsername(), result.getUsername());

    verify(userRepository, times(1)).save(user1);
  }

  @Test
  @DisplayName("Should successfully update a user")
  void updateUser() {

    UserDto payload = new UserDto(
        user2.getId(),
        "Jane The Smith",
        "jane.smith@example.com",
        "janesmith",
        user2.getCreatedAt()
    );

    User updatedUser = new User(
        payload.getFullName(),
        payload.getEmail(),
        user2.getPassword(),
        payload.getUsername()
    );
    updatedUser.setId(user2.getId());

    when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);

    UserDto result = userService.updateUser(payload);

    assertEquals(payload.getFullName(), result.getFullName());
    assertEquals(payload.getEmail(), result.getEmail());
    assertEquals(payload.getUsername(), result.getUsername());

    verify(userRepository, times(1)).findById(user2.getId());
    verify(userRepository, times(1)).save(
        any(User.class));
  }

  @Test
  @DisplayName("Should delete user successfully")
  void deleteUser_success() {
    UUID userId = user2.getId();
    when(userRepository.existsById(userId)).thenReturn(true);

    assertDoesNotThrow(() -> userService.deleteUser(userId));

    verify(userRepository, times(1)).deleteById(userId);
    verify(userRepository, times(1)).existsById(userId);
  }


}
