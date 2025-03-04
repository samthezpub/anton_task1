package com.example.anton_task1.Response.ProjectController;

import com.example.anton_task1.DTO.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateProjectResponse {
    private String message;
    private ProjectDTO project;
}
