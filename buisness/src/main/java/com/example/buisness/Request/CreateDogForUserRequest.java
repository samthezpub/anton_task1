package com.example.buisness.Request;

import com.example.buisness.DTO.DogDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDogForUserRequest {
  private Long userId;
  private DogDTO dogDTO;
}
