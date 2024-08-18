package com.alexandria.model.entity;

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
  private List<Book> books;

}
