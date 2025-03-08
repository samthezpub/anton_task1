package com.example.buisness.Response.CarController;

import com.example.buisness.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponse {
  private Long id;
  private CarDTO carDTO;
  private String message;
}
