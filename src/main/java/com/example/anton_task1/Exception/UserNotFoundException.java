package com.example.anton_task1.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends Exception {
  private Long userId;
  private final Integer code = 404;

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Long userId) {
    super(message);
    this.userId = userId;
  }
}
