package com.example.buisness.Response.CourseController;

import com.example.buisness.DTO.CourseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindAllCoursesResponse {
  private String message;
  private List<CourseDTO> courses;
}
