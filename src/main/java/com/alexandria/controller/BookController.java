package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.service.*;
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
  public ResponseEntity<Book> postBook(@RequestBody Book book) {
    return ResponseEntity.ok(this.bookService.postBook(book));
  }
}
