package com.alexandria.model.entities;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity<UUID> {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String fullName;
  private String email;
  private String password;
  private String username;


}