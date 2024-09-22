package com.alexandria.model.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author extends IdentityGenerator<UUID> {

  private String fullName;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Book> books;

  public Author(String fullName) {
    this.fullName = fullName;
  }
}
