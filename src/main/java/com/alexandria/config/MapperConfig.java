package com.alexandria.config;

import com.alexandria.dto.*;
import com.alexandria.model.entity.*;
import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.context.annotation.*;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // Customize the mappings here
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    // Explicit converter for books
    modelMapper.addConverter(new BookToBookDtoConverter());

    return modelMapper;
  }

  private static class BookToBookDtoConverter extends AbstractConverter<Book, BookDto> {

    @Override
    protected BookDto convert(Book book) {
      BookDto bookDto = new BookDto();

      bookDto.setId(book.getId());

      bookDto.setTitle(book.getTitle());

      bookDto.setPublishedDate(book.getPublishedDate());
      // Map genres
      bookDto.setBookGenres(

          book.getGenres().stream().map(Genre::getName).toList()
      );

      // Map author name
      bookDto.setAuthorName(book.getAuthor().getFullName());

      // Map publisher name
      bookDto.setPublisherName(book.getPublisher().getName());

      return bookDto; // Return the populated BookDto
    }
  }

}
