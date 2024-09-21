package com.alexandria.dto;

import java.util.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

  private String title;
  private List<UUID> genreIds;
  private String authorName;
  private String publisherName;
}
