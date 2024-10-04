package com.alexandria.service;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import com.alexandria.model.repository.*;
import java.util.*;
import java.util.stream.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Service
public class AuthorService extends CrudServiceImpl<AuthorRepository, Author, UUID, AuthorDto> {

  public AuthorService(AuthorRepository repository, ModelMapper modelMapper) {
    super(repository, modelMapper);
  }

  public Author createOrFindAuthor(String authorName) {
    Optional<Author> author = repository.findByFullName(authorName);

    return author.orElseGet(() -> createElement(new Author(authorName)));
  }

  public List<AuthorDto> getAuthors() {
    List<Author> authors = findAll();

    return convertToListDto(findAll());
  }


  @Override
  public List<AuthorDto> convertToListDto(List<Author> authors) {
    return authors.stream().map(author -> modelMapper.map(author, AuthorDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public AuthorDto convertToDetailDto(Author element) {
    return null;
  }
}
