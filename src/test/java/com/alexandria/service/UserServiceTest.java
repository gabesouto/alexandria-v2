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
    // Inject the mock repository via the constructor
    userService = new UserService(userRepository);

  }

  @Test
  @DisplayName("Should return all users without passwords")
  void getUsers() {
    // Define behavior of the mock repository
    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    // Call the method to test
    List<UserDto> result = userService.getUsers();

    // Verify the size of the returned list
    assertEquals(2, result.size(), "The result list should contain 2 users");

    // Verify the content of the returned list
    assertEquals("John Doe", result.get(0).getFullName());
    assertEquals("john.doe@example.com", result.get(0).getEmail());

    assertEquals("Jane Smith", result.get(1).getFullName());
    assertEquals("jane.smith@example.com", result.get(1).getEmail());

    // Verify the interaction with the mock repository
    verify(userRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should successfully add a new user")
  void addUser() {
    // Define behavior of the mock repository
    when(userRepository.save(user1)).thenReturn(user1);

    UserCreationDto userCreationDto = userService.convertTo(user1, UserCreationDto.class);

    // Call the method to test
    UserDto result = userService.addUser(userCreationDto);

    // Verify that the returned UserDto matches the input UserDto
    assertEquals(user1.getFullName(), result.getFullName());
    assertEquals(user1.getEmail(), result.getEmail());
    assertEquals(user1.getUsername(), result.getUsername());

    // Verify the interaction with the mock repository
    verify(userRepository, times(1)).save(user1);
  }

  @Test
  @DisplayName("Should successfully update a user")
  void updateUser() {
    // Create a UserDto with updated values
    UserDto payload = new UserDto(
        user2.getId(),
        "Jane The Smith",
        "jane.smith@example.com",
        "janesmith",
        user2.getCreatedAt()
    );

    // Create an updated User entity with the new values
    User updatedUser = new User(
        payload.getFullName(),
        payload.getEmail(),
        user2.getPassword(),
        payload.getUsername()
    );
    updatedUser.setId(user2.getId());  // Ensure IDs match

    // Mock the repository behavior
    when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);  // Mock saving updated user

    // Call the method to test
    UserDto result = userService.updateUser(payload);

    // Assertions
    assertEquals(payload.getFullName(), result.getFullName());
    assertEquals(payload.getEmail(), result.getEmail());
    assertEquals(payload.getUsername(), result.getUsername());

    // Verify interactions
    verify(userRepository, times(1)).findById(user2.getId());
    verify(userRepository, times(1)).save(
        any(User.class));  // Ensure `save` is called with an updated user
  }


}
