package com.example.buisness.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {
  private Long id;
  private String name;
  private String description;
}
