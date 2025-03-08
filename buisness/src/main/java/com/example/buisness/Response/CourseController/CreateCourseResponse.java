package com.example.buisness.Response.CourseController;

import com.example.buisness.DTO.CourseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseResponse {
  private String message;
  private CourseDTO course;
}
