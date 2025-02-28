package com.example.anton_task1.Response.CarController;

import com.example.anton_task1.DTO.CarDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindResponse {
    private Long id;
    private String message;
    private CarDTO car;
}
