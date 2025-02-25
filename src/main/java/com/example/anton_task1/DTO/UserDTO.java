package com.example.anton_task1.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;

  private String username;
  private String password;

  private String email;
  private String phone;
}
