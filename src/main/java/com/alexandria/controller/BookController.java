package com.alexandria.controller;

import com.alexandria.dto.*;
import com.alexandria.service.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

  public final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<BookDto> getBooks() {
    return bookService.getBooks();
  }
}
