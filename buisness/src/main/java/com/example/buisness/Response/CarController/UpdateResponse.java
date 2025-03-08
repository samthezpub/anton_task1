package com.example.buisness.Response.CarController;

import com.example.buisness.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponse {
  private Long id;
  private CarDTO carDTO;
  private String message;
}
