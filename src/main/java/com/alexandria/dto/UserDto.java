package com.alexandria.dto;


import java.util.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

  private String fullName;

  private String email;

  private String username;

  private Date createdAt;
}
