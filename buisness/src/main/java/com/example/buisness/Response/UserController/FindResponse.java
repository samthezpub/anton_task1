package com.example.buisness.Response.UserController;

import com.example.buisness.DTO.UserAndCatDTO;
import com.example.buisness.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindResponse {
  private Long id;
  private UserAndCatDTO dto;
  private String message;
}
