package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

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

    User user1 = new User("John Doe", "john.doe@example.com", "password456", "johndoe");
    User user2 = new User("Jane Smith", "jane.smith@example.com", "password456", "janesmith");

    // Define behavior of the mock repository
    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    // Call the method to test
    List<User> result = userService.getUsers();

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
}
