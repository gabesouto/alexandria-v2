package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.exception.*;
import com.alexandria.infra.security.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.enums.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

@Service
public class AuthenticationService extends CrudServiceImpl<UserRepository, User, UUID, UserDto> {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public AuthenticationService(UserRepository repository, ModelMapper modelMapper,
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    super(repository, modelMapper);
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  public UserDto createUser(UserCreationDto userCreationDto) {
    if (this.repository.findByEmail(userCreationDto.getEmail()) != null) {
      throw new UserAlreadyExistsException();
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(userCreationDto.getPassword());

    User newUser = new User(userCreationDto.getFullName(), userCreationDto.getEmail(),
        encryptedPassword,
        userCreationDto.getUsername(), UserRole.USER);

    ;

    return convertToDetailDto(this.repository.save(newUser));
  }

  public LoginResponseDto login(AuthenticationDto authenticationDto) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(),
        authenticationDto.getPassword());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return new LoginResponseDto(token);
  }

  @Override
  public List<UserDto> convertToListDto(List<User> elements) {
    return List.of();
  }

  @Override
  public UserDto convertToDetailDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }
}
