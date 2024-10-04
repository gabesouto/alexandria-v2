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
public class AuthorServiceTest {

  Author author1 = new Author("J.K. Rowling");
  Author author2 = new Author("George R.R. Martin");

  @Mock
  private AuthorRepository authorRepository;
  @Mock
  private AuthorService authorService;
  @Mock
  private ModelMapper modelMapper;

  @BeforeEach
  void setUp() {
    authorService = new AuthorService(authorRepository, modelMapper);
  }

  @Test
  @DisplayName("Author found")
  void testCreateOrFindAuthorWhenAuthorExists() {
    // Arrange: Mock the behavior of the repository
    when(authorRepository.findByFullName("John Doe")).thenReturn(Optional.of(author1));

    // Act: Call the method
    Author foundAuthor = authorService.createOrFindAuthor("John Doe");

    // Assert: Verify the results
    assertEquals(author1, foundAuthor);
    verify(authorRepository).findByFullName("John Doe");
    verifyNoMoreInteractions(authorRepository); // Verify no other interactions
  }

  @Test
  @DisplayName("Author not found, new Author created")
  void testCreateOrFindAuthorWhenAuthorDoesNotExist() {
    // Arrange: Mock the behavior of the repository to return an empty Optional
    when(authorRepository.findByFullName(author2.getFullName())).thenReturn(Optional.empty());
    when(authorRepository.save(author2)).thenReturn(author2);

    // Act: Call the method
    Author newAuthor = authorService.createOrFindAuthor(author2.getFullName());

    // Assert: Verify the results
    assertNotNull(newAuthor);
    assertEquals(author2.getFullName(), newAuthor.getFullName());
    verify(authorRepository).findByFullName(author2.getFullName());
    // Verify that createElement was called
    verify(authorRepository, times(1)).save(
        newAuthor); // Assuming createElement saves the new author
  }

  @Test
  @DisplayName("Should return all authors")
  void getAuthors() {

    AuthorDto authorDto1 = new AuthorDto(author1.getFullName());

    AuthorDto authorDto2 = new AuthorDto(author2.getFullName());
    
    when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));
    when(modelMapper.map(author1, AuthorDto.class)).thenReturn(authorDto1);
    when(modelMapper.map(author2, AuthorDto.class)).thenReturn(authorDto2);

    List<AuthorDto> result = authorService.getAuthors();

    assertEquals(2, result.size(), "The result list should contain 2 authors");

    assertEquals(author1.getFullName(), result.get(0).getFullName());
    assertEquals(author2.getFullName(), result.get(1).getFullName());
  }

}
