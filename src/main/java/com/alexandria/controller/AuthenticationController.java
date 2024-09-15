package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.infra.security.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.enums.*;
import com.alexandria.model.repository.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

  private final UserRepository userRepository;

  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  public AuthenticationController(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto payload) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(payload.getEmail(),
        payload.getPassword());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody UserCreationDto payload) {
    if (this.userRepository.findByEmail(payload.getEmail()) != null) {
      return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(payload.getPassword());

    User newUser = new User(payload.getFullName(), payload.getEmail(), encryptedPassword,
        payload.getUsername(), UserRole.USER);

    this.userRepository.save(newUser);

    return ResponseEntity.ok(newUser);
  }
}
