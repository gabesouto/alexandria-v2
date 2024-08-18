package com.alexandria.model.entities;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book extends IdentityGenerator<UUID> {

  private String title;
  private String authorName;
  private String publisher;
  private String genre;
  private Date publishedDate;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

}
