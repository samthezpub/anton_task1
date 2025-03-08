package com.example.buisness.Response.CarController;

import com.example.buisness.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindResponse {
  private Long id;
  private CarDTO car;
  private String message;
}
