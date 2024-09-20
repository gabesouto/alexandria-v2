package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.modelmapper.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  // Create sample authors and publishers
  Author author1 = new Author("J.K. Rowling");
  Author author2 = new Author("George R.R. Martin");

  Publisher publisher1 = new Publisher("Bloomsbury Publishing");
  Publisher publisher2 = new Publisher("Bantam Books");

  // Create sample genres
  Genre fantasy = new Genre("Fantasy");
  Genre adventure = new Genre("Adventure");
  Genre epic = new Genre("Epic");

  Book book1 = new Book("Harry Potter and the Philosopher's Stone",
      "J.K Rowling",
      new Date(),
      publisher1.getName());

  Book book2 = new Book(
      "A Game of Thrones",
      "George R.R. Martin",
      new Date(),
      publisher2.getName()
  );


  @Mock
  private BookRepository bookRepository;
  private BookService bookService;

  @Mock
  private ModelMapper modelMapper;


  @BeforeEach
  void setUp() {

    bookService = new BookService(bookRepository, modelMapper);

  }

  @Test
  @DisplayName("Should return all books")
  void getBooks() {
    book1.setGenres(Arrays.asList(fantasy, adventure));
    book2.setGenres(Arrays.asList(fantasy, epic));

    List<String> bookGenres1 = book1.getGenres().stream().map(Genre::getName).toList();

    List<String> bookGenres2 = book2.getGenres().stream().map(Genre::getName).toList();
    when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

    // Mocking the ModelMapper behavior for each book

    BookDto bookDto1 = new BookDto(book1.getTitle(), book1.getAuthorName(),
        book1.getPublishedDate(), book1.getPublisherName(), bookGenres1);

    BookDto bookDto2 = new BookDto(book2.getTitle(), book2.getAuthorName(),
        book2.getPublishedDate(), book2.getPublisherName(), bookGenres2);

    // Tell Mockito to return the correct BookDto when modelMapper.map is called
    when(modelMapper.map(book1, BookDto.class)).thenReturn(bookDto1);
    when(modelMapper.map(book2, BookDto.class)).thenReturn(bookDto2);

    List<BookDto> result = bookService.getBooks();

    assertEquals(2, result.size(), "The result list should contain 2 books");

    assertEquals("J.K Rowling", result.get(0).getAuthorName());
    assertEquals("Harry Potter and the Philosopher's Stone", result.get(0).getTitle());

    assertEquals("George R.R. Martin", result.get(1).getAuthorName());
    assertEquals(bookGenres2.get(1), result.get(1).getBookGenres().get(1));
    assertEquals("A Game of Thrones", result.get(1).getTitle());

    verify(bookRepository, times(1)).findAll();
  }


}
