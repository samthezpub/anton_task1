package com.example.anton_task1.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogNotFoundException extends Exception {
  private Long id;
  private final Integer code = 404;

  public DogNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }
}
