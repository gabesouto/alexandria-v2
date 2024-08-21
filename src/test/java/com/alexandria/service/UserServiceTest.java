package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.*;

public class UserServiceTest {

  @MockBean
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return all users without passwords")
  void getUsers() {
    // Arrange: Create sample users with passwords
    User user1 = new User(1L, "John Doe", "john.doe@example.com", "password123");
    User user2 = new User(2L, "Jane Smith", "jane.smith@example.com", "password456");

    // Mock the repository's behavior to return the users
    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    // Act: Call the service method to get the users
    List<UserDto> result = userService.getAllUsersWithoutPasswords();

    // Assert: Verify that the returned users have their passwords removed
    assertEquals(2, result.size());
    assertEquals("John Doe", result.get(0).getFullName());
    assertEquals("john.doe@example.com", result.get(0).getEmail());
    assertEquals(null, result.get(0).getPassword()); // Password should be null

    assertEquals("Jane Smith", result.get(1).getFullName());
    assertEquals("jane.smith@example.com", result.get(1).getEmail());
    assertEquals(null, result.get(1).getPassword()); // Password should be null
  }
}