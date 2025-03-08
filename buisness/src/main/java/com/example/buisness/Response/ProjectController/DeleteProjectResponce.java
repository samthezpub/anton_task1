package com.example.buisness.Response.ProjectController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteProjectResponce {
  private String message;
  private Long id;
}
