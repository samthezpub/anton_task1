package com.example.buisness.Response.DogController;

import com.example.buisness.DTO.DogDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindResponse {
  private Long id;
  private String message;
  private DogDTO dog;
}
