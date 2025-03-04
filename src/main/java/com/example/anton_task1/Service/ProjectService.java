package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.Exception.ProjectNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.AddOrRemoveUserFromProjectRequest;
import com.example.anton_task1.Response.ProjectController.CreateProjectResponse;
import com.example.anton_task1.Response.ProjectController.DeleteProjectResponce;
import com.example.anton_task1.Response.ProjectController.FindProjectByIdResponse;
import com.example.anton_task1.Response.ProjectController.UpdateProjectResponse;

public interface ProjectService {
    CreateProjectResponse createProject(ProjectDTO projectDTO);
    FindProjectByIdResponse findProjectById(Long id) throws ProjectNotFoundException;
    UpdateProjectResponse updateProject(ProjectDTO projectDTO);
    UpdateProjectResponse addUserToProject(AddOrRemoveUserFromProjectRequest request) throws UserNotFoundException, ProjectNotFoundException;
    UpdateProjectResponse removeUserFromProject(AddOrRemoveUserFromProjectRequest request) throws UserNotFoundException, ProjectNotFoundException;
    DeleteProjectResponce deleteProject(Long id);
}
