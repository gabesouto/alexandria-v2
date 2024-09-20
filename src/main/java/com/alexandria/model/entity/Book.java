package com.alexandria.model.entity;

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
  private Date publishedDate;
  private String publisherName;
  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;
  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;
  @ManyToMany(mappedBy = "books")
  private List<Genre> genres = new ArrayList<>();

  public Book(String title, String authorName, Date publishedDate, String publisherName) {
    this.authorName = authorName;
    this.title = title;
    this.publishedDate = publishedDate;
    this.publisherName = publisherName;
  }
}
