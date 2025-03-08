package com.example.buisness.Response.CourseController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteByIdResponse {
  private Long id;
  private String message;
}
