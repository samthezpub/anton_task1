package com.example.anton_task1.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseNotFound extends Exception {
  private final Integer code = 404;
  private Long id;

  public CourseNotFound(String message) {
    super(message);
  }

  public CourseNotFound(String message, Long id) {
    super(message);
    this.id = id;
  }
}
