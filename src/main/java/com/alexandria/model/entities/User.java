package com.alexandria.model.entities;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends IdentityGenerator<UUID> {

  private String fullName;
  private String email;
  private String password;
  private String username;


}