package com.alexandria.model.enums;

import lombok.*;

@Getter
public enum UserRole {
  ADMIN("admin"),
  USER("user");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

}
