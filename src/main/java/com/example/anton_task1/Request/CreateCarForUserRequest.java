package com.example.anton_task1.Request;

import com.example.anton_task1.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarForUserRequest {
  private CarDTO carDTO;
  private Long userId;
}
