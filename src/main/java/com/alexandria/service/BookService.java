package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import java.util.stream.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class BookService extends CrudServiceImpl<BookRepository, Book, UUID, BookDto> {

  private final ModelMapper modelMapper;

  // Constructor that accepts both BookRepository and ModelMapper
  public BookService(BookRepository repository, ModelMapper modelMapper) {
    super(repository);
    this.modelMapper = modelMapper;
  }


  public List<BookDto> getBooks() {
    List<Book> books = findAll();
    return convertToListDto(books);
  }

  public Book postBook(Book book) {
    return createElement(book);
  }

  @Override
  public List<BookDto> convertToListDto(List<Book> books) {
    return books.stream().map(book -> modelMapper.map(book, BookDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public BookDto convertToDetailDto(Book element) {
    return null;
  }
}
