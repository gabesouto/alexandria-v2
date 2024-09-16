package com.alexandria.service;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public class BookService extends CrudServiceImpl<BookRepository, Book, UUID, Book> {

  public BookService(BookRepository repository) {
    super(repository);
  }

  public List<Book> getBooks() {
    return findAll();
  }

  @Override
  public List<Book> convertToListDto(List<Book> elements) {
    return List.of();
  }

  @Override
  public Book convertToDetailDto(Book element) {
    return null;
  }
}
