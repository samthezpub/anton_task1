package com.example.anton_task1.Exception;

public class ProjectNotFoundException extends RuntimeException {
  private final Integer code = 404;
  private Long id;

  public ProjectNotFoundException(String message) {
    super(message);
  }

  public ProjectNotFoundException(String message, Long id) {
    super(message);
    this.id = id;
  }
}
