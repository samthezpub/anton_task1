package com.example.buisness.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarNotFoundException extends Exception {
  private Long id;
  private final Integer code = 404;

  public CarNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }
}
