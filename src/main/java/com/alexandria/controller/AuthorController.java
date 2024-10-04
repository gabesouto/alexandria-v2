package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public List<AuthorDto> getBooks() {
    return authorService.getAuthors();
  }
}
