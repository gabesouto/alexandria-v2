package com.alexandria.dto;

import java.util.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

  private String title;
  private String authorName;
  private Date publishedDate;
  private String publisherName;
  private List<String> bookGenres = new ArrayList<>();


}
