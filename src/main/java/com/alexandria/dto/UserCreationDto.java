package com.alexandria.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationDto {

  @NotBlank(message = "Full name is mandatory")
  private String fullName;

  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Username is mandatory")
  private String username;

  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, message = "Password should be at least 8 characters long")
  private String password;
}
