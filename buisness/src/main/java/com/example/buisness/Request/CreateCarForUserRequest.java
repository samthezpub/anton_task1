package com.example.buisness.Request;

import com.example.buisness.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarForUserRequest {
  private CarDTO carDTO;
  private Long userId;
}
