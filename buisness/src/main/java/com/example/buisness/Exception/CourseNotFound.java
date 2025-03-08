package com.example.buisness.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseNotFound extends Exception {
  private Long id;
  private final Integer code = 404;

  public CourseNotFound(String message) {
    super(message);
  }

  public CourseNotFound(String message, Long id) {
    super(message);
    this.id = id;
  }
}
