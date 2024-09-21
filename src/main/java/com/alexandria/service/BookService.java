package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class BookService extends CrudServiceImpl<BookRepository, Book, UUID, BookDto> {


  private final GenreRepository genreRepository;
  private AuthorService authorService;
  private PublisherService publisherService;

  public BookService(BookRepository repository,
      ModelMapper modelMapper,
      AuthorService authorService,
      GenreRepository genreRepository,
      PublisherService publisherService) {
    super(repository, modelMapper);
    this.authorService = authorService;
    this.genreRepository = genreRepository;
    this.publisherService = publisherService;
  }

  public List<BookDto> getBooks() {
    List<Book> books = findAll();
    return convertToListDto(books);
  }

  public BookDto createBook(CreateBookRequest book) {
    Author author = authorService.createOrFindAuthor(book.getAuthorName());

    Publisher publisher = publisherService.createOrFindPublisher(book.getPublisherName());

    List<Genre> genres = genreRepository.findAllById(book.getGenreIds());

    Book newBook = new Book(book.getTitle(), author, new Date(), publisher, genres);
    newBook.setGenres(genres);

    createElement(newBook);

    return convertToDetailDto(newBook);
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
  public BookDto convertToDetailDto(Book book) {
    return modelMapper.map(book, BookDto.class);
  }
}
