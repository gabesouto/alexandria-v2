package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
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

  @GetMapping
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(this.userService.getUsers());
  }

  @PostMapping
  public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserCreationDto userCreationDto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userCreationDto));
  }

  @PutMapping
  public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(this.userService.updateUser(userDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    this.userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

}
