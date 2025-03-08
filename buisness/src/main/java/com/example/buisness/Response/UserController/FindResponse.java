package com.example.buisness.Response.UserController;

import com.example.buisness.DTO.UserDTO;
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
