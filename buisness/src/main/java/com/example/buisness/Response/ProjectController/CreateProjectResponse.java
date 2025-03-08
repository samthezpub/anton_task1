package com.example.buisness.Response.ProjectController;

import com.example.buisness.DTO.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateProjectResponse {
  private String message;
  private ProjectDTO project;
}
