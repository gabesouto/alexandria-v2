package com.alexandria.model.entity;

import jakarta.persistence.*;
import java.io.*;
import lombok.*;

@Setter
@Getter
@MappedSuperclass
public abstract class IdentityGenerator<ID extends Serializable> extends BaseEntity<ID> {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private ID id;
}