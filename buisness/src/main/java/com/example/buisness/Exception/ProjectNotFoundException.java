package com.example.buisness.Exception;

public class ProjectNotFoundException extends RuntimeException {
  private Long id;
  private final Integer code = 404;

  public ProjectNotFoundException(String message) {
    super(message);
  }

  public ProjectNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }
}
