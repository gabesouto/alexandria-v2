package com.alexandria.model.entity;


import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
public class Genre extends IdentityGenerator<UUID> {

  @ManyToMany
  @JoinTable(
      name = "book_genres",
      joinColumns = @JoinColumn(name = "genre_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  List<Book> books = new ArrayList<>();
  private String name;
  public Genre(String name) {
    this.name = name;
  }
}
