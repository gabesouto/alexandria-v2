package com.alexandria.dto;

import java.util.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDto {

  private String title;
  private List<UUID> genreIds;
  private String authorName;
  private String publisherName;
}
