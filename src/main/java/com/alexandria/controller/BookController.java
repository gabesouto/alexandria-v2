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
@RequestMapping("books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @Operation(summary = "Get all books", description = "Retrieves a list of all books in the system.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public List<BookDto> getBooks() {
    return bookService.getBooks();
  }

  @Operation(summary = "Create a new book", description = "Creates a new book with the provided details.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book created successfully",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad request - invalid data")
  })
  @PostMapping
  public ResponseEntity<BookDto> postBook(@Valid @RequestBody CreateBookRequestDto book) {
    return ResponseEntity.ok(this.bookService.createBook(book));
  }

  @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @DeleteMapping("{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
    this.bookService.deleteBook(bookId);
    return ResponseEntity.noContent().build();
  }
}
