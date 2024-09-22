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
      author1,
      new Date(),
      publisher1,
      Arrays.asList(fantasy, adventure));

  Book book2 = new Book(
      "A Game of Thrones",
      author2,
      new Date(),
      publisher2,
      Arrays.asList(fantasy, epic)
  );


  @Mock
  private BookRepository bookRepository;
  @Mock
  private AuthorService authorService;
  @Mock
  private GenreRepository genreRepository;
  @Mock
  private PublisherService publisherService;
  private BookService bookService;
  @Mock
  private ModelMapper modelMapper;

  private List<String> getBookGenres(Book book) {
    return book.getGenres().stream().map(Genre::getName).toList();
  }

  private List<UUID> getBookGenresIds(Book book) {
    return book.getGenres().stream().map(Genre::getId).toList();
  }

  @BeforeEach
  void setUp() {
    bookService = new BookService(bookRepository, modelMapper, authorService, genreRepository,
        publisherService);
  }

  @Test
  @DisplayName("Should return all books")
  void getBooks() {

    when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

    // Mocking the ModelMapper behavior for each book
    BookDto bookDto1 = new BookDto(book1.getTitle(), book1.getId(), book1.getAuthor().getFullName(),
        book1.getPublishedDate(), book1.getPublisher().getName(), getBookGenres(book1));

    BookDto bookDto2 = new BookDto(book2.getTitle(), book2.getId(), book2.getAuthor().getFullName(),
        book2.getPublishedDate(), book2.getPublisher().getName(), getBookGenres(book2));

    // Tell Mockito to return the correct BookDto when modelMapper.map is called
    when(modelMapper.map(book1, BookDto.class)).thenReturn(bookDto1);
    when(modelMapper.map(book2, BookDto.class)).thenReturn(bookDto2);

    List<BookDto> result = bookService.getBooks();

    assertEquals(2, result.size(), "The result list should contain 2 books");
    assertEquals("J.K. Rowling", result.get(0).getAuthorName());
    assertEquals("Harry Potter and the Philosopher's Stone", result.get(0).getTitle());
    assertEquals("George R.R. Martin", result.get(1).getAuthorName());
    assertEquals("A Game of Thrones", result.get(1).getTitle());

    // Verify the genres for the second book
    assertEquals(getBookGenres(book2).get(1), result.get(1).getBookGenres().get(1));

    verify(bookRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should successfully add a new book")
  void addBook() {

    when(bookRepository.save(any(Book.class))).thenReturn(book1);

    CreateBookRequestDto createBookRequest = new CreateBookRequestDto(book1.getTitle(),
        getBookGenresIds(book1),
        book1.getAuthor().getFullName(), book1.getPublisher().getName());

    // Simulate the addBook method
    // Mocking the ModelMapper behavior for each book
    BookDto bookDto1 = new BookDto(book1.getTitle(), book1.getId(), book1.getAuthor().getFullName(),
        book1.getPublishedDate(), book1.getPublisher().getName(), getBookGenres(book1));

    when(modelMapper.map(any(Book.class), eq(BookDto.class))).thenReturn(bookDto1);

    BookDto result = bookService.createBook(createBookRequest);

    assertNotNull(result);
    assertEquals(book1.getTitle(), result.getTitle());
    assertEquals(book1.getAuthor().getFullName(), result.getAuthorName());

    verify(bookRepository, times(1)).save(any(Book.class));
  }

  @Test
  @DisplayName("Should delete a book")
  void deleteBook() {
    when(bookRepository.existsById(book1.getId())).thenReturn(true);

    assertDoesNotThrow(() -> bookService.deleteBook(book1.getId()));

    verify(bookRepository, times(1)).deleteById(book1.getId());
    verify(bookRepository, times(1)).existsById(book1.getId());
  }
}
