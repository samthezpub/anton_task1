package com.example.buisness.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserFromCourseRequest {
  private Long userId;
  private Long courseId;
}
