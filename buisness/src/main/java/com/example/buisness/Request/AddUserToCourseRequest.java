package com.example.buisness.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserToCourseRequest {
  private Long userId;
  private Long courseId;
}
