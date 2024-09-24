package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.dto.*;
import com.alexandria.exception.*;
import com.alexandria.infra.security.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.enums.*;
import com.alexandria.model.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.modelmapper.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

  @Mock
  private UserRepository userRepository;
  private AuthenticationService authenticationService;
  @Mock
  private TokenService tokenService;
  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private ModelMapper modelMapper;


  @BeforeEach
  void setUp() {

    authenticationService = new AuthenticationService(userRepository, modelMapper,
        authenticationManager, tokenService);

  }

  @Test
  @DisplayName("Should successfully log a user")
  void login() {
    AuthenticationDto authenticationDto = new AuthenticationDto("test@test.com", "12345678");

    var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(),
        authenticationDto.getPassword());

    var mockUser = Mockito.mock(User.class);

    var mockAuthentication = Mockito.mock(Authentication.class);

    var token = "token";

    when(authenticationManager.authenticate(usernamePassword)).thenReturn(mockAuthentication);
    when(mockAuthentication.getPrincipal()).thenReturn(mockUser);
    when(tokenService.generateToken(mockUser)).thenReturn(token);

    LoginResponseDto result = authenticationService.login(authenticationDto);

    assertNotNull(result);
    assertEquals(token, result.token());

    verify(authenticationManager).authenticate(usernamePassword);
    verify(tokenService).generateToken(mockUser);
  }

  @Test
  @DisplayName("Should throw AuthenticationException when login fails")
  void loginShouldThrowAuthenticationException() {
    AuthenticationDto authenticationDto = new AuthenticationDto("wrong@test.com", "wrongpassword");

    var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(),
        authenticationDto.getPassword());

    when(authenticationManager.authenticate(usernamePassword))
        .thenThrow(new AuthenticationException("Invalid credentials") {
        });

    AuthenticationException exception = assertThrows(AuthenticationException.class, () -> {
      authenticationService.login(authenticationDto);
    });

    assertEquals("Invalid credentials", exception.getMessage());

    verify(tokenService, never()).generateToken(any());
  }

  @Test
  @DisplayName("Should sucessfully create a new user")
  void createUser() {

    UserCreationDto userCreationDto = new UserCreationDto("test 1", "test@test.com", "test1",
        "encryptedPassword");

    User user = new User("test 1", "test@test.com", "encryptedPassword", "test1", UserRole.USER);

    UserDto userDto = new UserDto(user.getId(), user.getFullName(), user.getEmail(),
        user.getUsername(), user.getCreatedAt());

    when(userRepository.save(any(User.class))).thenReturn(user);
    when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);

    UserDto result = authenticationService.createUser(userCreationDto);

    assertNotNull(result);
    assertEquals(user.getFullName(), result.getFullName());
    assertEquals(user.getCreatedAt(), result.getCreatedAt());

    verify(userRepository, times(1)).save(any(User.class));

  }

  @Test
  @DisplayName("Should throw a UserAlreadyExistsException when email already exists")
  void createUserShouldThrowUserAlreadyExistsException() {
    UserCreationDto userCreationDto = new UserCreationDto("test 1", "test@test.com", "test1",
        "encryptedPassword");

    User existingUser = new User();
    when(userRepository.findByEmail(userCreationDto.getEmail())).thenReturn(existingUser);

    UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
      authenticationService.createUser(userCreationDto);
    });

    assertEquals("User already exists.", exception.getMessage());

    verify(userRepository).findByEmail(userCreationDto.getEmail());
  }


}
