package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Get all users", description = "Retrieves a list of all registered users.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(this.userService.getUsers());
  }

  @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created successfully",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad request - invalid data")
  })
  @PostMapping
  public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserCreationDto userCreationDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userCreationDto));
  }

  @Operation(summary = "Update an existing user", description = "Updates an existing user with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @PutMapping
  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(this.userService.updateUser(userDto));
  }

  @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "User deleted successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    this.userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
