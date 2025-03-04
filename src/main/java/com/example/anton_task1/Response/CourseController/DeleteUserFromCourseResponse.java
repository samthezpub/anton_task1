package com.example.anton_task1.Response.CourseController;

import com.example.anton_task1.DTO.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteUserFromCourseResponse {
  private String message;
  private CourseDTO course;
}
