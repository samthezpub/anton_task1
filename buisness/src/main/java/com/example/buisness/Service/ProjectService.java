package com.example.buisness.Service;

import com.example.buisness.DTO.ProjectDTO;
import com.example.buisness.Exception.ProjectNotFoundException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.AddOrRemoveUserFromProjectRequest;
import com.example.buisness.Response.ProjectController.CreateProjectResponse;
import com.example.buisness.Response.ProjectController.DeleteProjectResponce;
import com.example.buisness.Response.ProjectController.FindProjectByIdResponse;
import com.example.buisness.Response.ProjectController.UpdateProjectResponse;

public interface ProjectService {
    CreateProjectResponse createProject(ProjectDTO projectDTO);
    FindProjectByIdResponse findProjectById(Long id) throws ProjectNotFoundException;
    UpdateProjectResponse updateProject(ProjectDTO projectDTO);
    UpdateProjectResponse addUserToProject(AddOrRemoveUserFromProjectRequest request) throws UserNotFoundException, ProjectNotFoundException;
    UpdateProjectResponse removeUserFromProject(AddOrRemoveUserFromProjectRequest request) throws UserNotFoundException, ProjectNotFoundException;
    DeleteProjectResponce deleteProject(Long id);
}
