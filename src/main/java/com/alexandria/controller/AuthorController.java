package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @Operation(summary = "Get all authors", description = "Retrieves a list of all registered authors.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of authors",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public List<AuthorDto> getAuthors() {
    return authorService.getAuthors();
  }
}
