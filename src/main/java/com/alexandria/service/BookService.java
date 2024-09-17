package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.stereotype.*;

@Service
public class BookService extends CrudServiceImpl<BookRepository, Book, UUID, BookDto> {

  public BookService(BookRepository repository) {
    super(repository);
  }

  public List<BookDto> getBooks() {
    List<Book> books = findAll();
    return convertToListDto(books);
  }

  @Override
  public List<BookDto> convertToListDto(List<Book> books) {
    return books.stream().map(user -> modelMapper.map(books, BookDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public BookDto convertToDetailDto(Book element) {
    return null;
  }
}
