package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.infra.security.*;
import com.alexandria.model.repository.*;
import com.alexandria.service.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      TokenService tokenService,
      AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto payload) {

    return ResponseEntity.ok(this.authenticationService.login(payload));
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody UserCreationDto payload) {

    return ResponseEntity.ok(this.authenticationService.createUser(payload));
  }
}
