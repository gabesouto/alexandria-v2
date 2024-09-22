package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
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

  @GetMapping
  public List<BookDto> getBooks() {
    return bookService.getBooks();
  }

  @PostMapping
  public ResponseEntity<BookDto> postBook(@Valid @RequestBody CreateBookRequestDto book) {
    return ResponseEntity.ok(this.bookService.createBook(book));
  }

  @DeleteMapping("{bookId}")
  public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId) {
    this.bookService.deleteBook(bookId);
    return ResponseEntity.noContent().build();
  }
}
