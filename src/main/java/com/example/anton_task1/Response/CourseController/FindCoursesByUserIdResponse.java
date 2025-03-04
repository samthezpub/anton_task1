package com.example.anton_task1.Response.CourseController;

import com.example.anton_task1.DTO.CourseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindCoursesByUserIdResponse {
  private String message;
  private List<CourseDTO> courses;
}
