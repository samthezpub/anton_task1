package com.example.anton_task1.Response.UserController;

import com.example.anton_task1.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindResponse {
  private Long id;
  private UserDTO user;
  private String message;
}
