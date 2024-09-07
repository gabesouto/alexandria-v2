package com.alexandria.controller;

import com.alexandria.dto.*;
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

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userCreationDto));
  }

  @PutMapping("update")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(this.userService.updateUser(userDto));
  }

}
