package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class BookService extends CrudServiceImpl<BookRepository, Book, UUID, BookDto> {


  // Constructor that accepts both BookRepository and ModelMapper
  public BookService(BookRepository repository, ModelMapper modelMapper) {
    super(repository, modelMapper);

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
    return books.stream().map(book -> {
      BookDto bookDto = modelMapper.map(book, BookDto.class);
      bookDto.setBookGenres(book.getGenres().stream()
          .map(Genre::getName) // Assuming Genre has a getName() method
          .toList());
      return bookDto;
    }).toList();
  }

  @Override
  public BookDto convertToDetailDto(Book element) {
    return null;
  }
}
