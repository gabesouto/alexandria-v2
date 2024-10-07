package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(
      AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized - invalid credentials")
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto payload) {
    return ResponseEntity.ok(this.authenticationService.login(payload));
  }

  @Operation(summary = "Register new user", description = "Registers a new user in the system.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad request - invalid data")
  })
  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody UserCreationDto payload) {
    return ResponseEntity.ok(this.authenticationService.createUser(payload));
  }
}
