package com.example.anton_task1.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExistsException extends Exception {
  private Integer code = 409;

  public UserExistsException(String message) {
    super(message);
  }

  public UserExistsException(String message, Integer code) {
    super(message);
    this.code = code;
  }
}
