package com.example.buisness.Request;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseRequest {
  private String name;

  @Nullable private String description;
}
