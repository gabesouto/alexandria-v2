package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.enums.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.modelmapper.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  User user1 = new User("John Doe", "john.doe@example.com", "password456", "johndoe",
      UserRole.ADMIN);
  User user2 = new User("Jane Smith", "jane.smith@example.com", "password456", "janesmith",
      UserRole.USER);

  @Mock
  private UserRepository userRepository;
  private UserService userService;

  @Mock
  private ModelMapper modelMapper;


  @BeforeEach
  void setUp() {

    userService = new UserService(userRepository, modelMapper);

  }

  @Test
  @DisplayName("Should return all users without passwords")
  void getUsers() {

    when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

    UserDto userDto1 = new UserDto(user1.getId(), user1.getFullName(), user1.getEmail(),
        user1.getUsername(), user1.getCreatedAt());
    UserDto userDto2 = new UserDto(user2.getId(), user2.getFullName(), user2.getEmail(),
        user2.getUsername(), user2.getCreatedAt());

    when(modelMapper.map(user1, UserDto.class)).thenReturn(userDto1);
    when(modelMapper.map(user2, UserDto.class)).thenReturn(userDto2);

    List<UserDto> result = userService.getUsers();

    assertEquals("John Doe", result.get(0).getFullName());
    assertEquals("john.doe@example.com", result.get(0).getEmail());

    assertEquals("Jane Smith", result.get(1).getFullName());
    assertEquals("jane.smith@example.com", result.get(1).getEmail());

    verify(userRepository, times(1)).findAll();
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
