package com.example.buisness.Response.CourseController;

import com.example.buisness.DTO.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindCourseByIdResponse {
  private String message;
  private CourseDTO course;
}
