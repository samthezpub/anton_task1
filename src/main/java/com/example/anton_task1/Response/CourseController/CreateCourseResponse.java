package com.example.anton_task1.Response.CourseController;

import com.example.anton_task1.DTO.CourseDTO;
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
