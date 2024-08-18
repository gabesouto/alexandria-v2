package com.alexandria.model.entity;

import jakarta.persistence.Version;
import jakarta.persistence.*;
import java.io.*;
import java.util.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID> implements Serializable {

  @Version
  @Column(nullable = false)
  protected Integer version;

  @Column
  @CreatedBy
  protected String createdBy;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdAt;

  @Column
  @LastModifiedBy
  protected String updatedBy;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  protected Date updatedAt;

  public abstract ID getId();


  @PrePersist
  @PreUpdate
  void onCreate() {
    if (createdAt == null) {
      setCreatedAt(new Date());
    }
  }
}