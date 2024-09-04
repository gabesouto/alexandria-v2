package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.service.*;
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

  @PostMapping("create")
  public ResponseEntity<UserDto> postUser(@RequestBody UserCreationDto userCreationDto) {
    User newUser = new User(userCreationDto.getFullName(), userCreationDto.getEmail(),
        userCreationDto.getPassword(), userCreationDto.getUsername());

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(newUser));
  }

}
