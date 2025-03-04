package com.example.anton_task1.Response.DogController;

import com.example.anton_task1.DTO.DogDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateResponse {
  private Long id;
  private String message;
  private DogDTO dogDTO;
}
