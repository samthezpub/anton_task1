package com.example.anton_task1.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarNotFoundException extends Exception {
  private final Integer code = 404;
  private Long id;

  public CarNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }
}
