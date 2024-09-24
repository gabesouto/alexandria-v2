package com.alexandria.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class AuthenticationDto {

  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, message = "Password should be at least 8 characters long")
  private String password;
}
