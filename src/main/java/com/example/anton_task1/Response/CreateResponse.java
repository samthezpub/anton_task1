package com.example.anton_task1.Response;

import com.example.anton_task1.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateResponse {
  private Long id;
  private String message;
  private UserDTO user;
}
