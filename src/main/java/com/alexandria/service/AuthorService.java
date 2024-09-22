package com.alexandria.service;

import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class AuthorService extends CrudServiceImpl<AuthorRepository, Author, UUID, Author> {

  public AuthorService(AuthorRepository repository, ModelMapper modelMapper) {
    super(repository, modelMapper);
  }

  public Author createOrFindAuthor(String authorName) {
    Optional<Author> author = repository.findByFullName(authorName);

    return author.orElseGet(() -> createElement(new Author(authorName)));
  }

  @Override
  public List<Author> convertToListDto(List<Author> elements) {
    return List.of();
  }

  @Override
  public Author convertToDetailDto(Author element) {
    return null;
  }
}
