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
@Table(name = "publishers")
public class Publisher extends IdentityGenerator<UUID> {

  private String name;

  @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Book> books;

  public Publisher(String name) {
    this.name = name;
  }
}
