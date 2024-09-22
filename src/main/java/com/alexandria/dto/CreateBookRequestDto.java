package com.alexandria.dto;

import jakarta.validation.constraints.*;
import java.util.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDto {

  @NotBlank(message = "title is mandatory")
  private String title;
  @NotEmpty(message = "Genre IDs are mandatory")
  private List<UUID> genreIds;
  @NotBlank(message = "Author name is mandatory")
  private String authorName;
  @NotBlank(message = "Publisher name  is mandatory")
  private String publisherName;
}
