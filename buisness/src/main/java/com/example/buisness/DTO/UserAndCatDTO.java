package com.example.buisness.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAndCatDTO {
  private UserDTO user;
  private CatDTO cat;
}
