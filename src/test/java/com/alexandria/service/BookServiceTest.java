package com.alexandria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

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

  // Create sample books using the constructor
  Book book1 = new Book(
      "Harry Potter and the Philosopher's Stone",
      "J.K. Rowling",
      new Date(),
      author1,
      publisher1,
      Arrays.asList(fantasy, adventure)
  );

  Book book2 = new Book(
      "A Game of Thrones",
      "George R.R. Martin",
      new Date(),
      author2,
      publisher2,
      Collections.singletonList(fantasy)
  );
  @Mock
  private BookRepository bookRepository;
  private BookService bookService;

  @BeforeEach
  void setUp() {

    bookService = new BookService(bookRepository);

  }

  @Test
  @DisplayName("Should return all books")
  void getUsers() {

    when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

    List<Book> result = bookService.getBooks();

    assertEquals(2, result.size(), "The result list should contain 2 books");

    assertEquals("J.K. Rowling", result.get(0).getAuthorName());
    assertEquals("Harry Potter and the Philosopher's Stone", result.get(0).getTitle());

    assertEquals("George R.R. Martin", result.get(1).getAuthorName());
    assertEquals("A Game of Thrones", result.get(1).getTitle());

    verify(bookRepository, times(1)).findAll();
  }


}
