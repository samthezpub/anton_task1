package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.Exception.ProjectNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.AddOrRemoveUserFromProjectRequest;
import com.example.anton_task1.Response.ProjectController.CreateProjectResponse;
import com.example.anton_task1.Response.ProjectController.DeleteProjectResponce;
import com.example.anton_task1.Response.ProjectController.FindProjectByIdResponse;
import com.example.anton_task1.Response.ProjectController.UpdateProjectResponse;
import com.example.anton_task1.Service.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/project")
@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {
  private final ProjectServiceImpl projectService;

  @Override
  public CreateProjectResponse createProject(ProjectDTO projectDTO) {
    return projectService.createProject(projectDTO);
  }

  @Override
  public FindProjectByIdResponse findProject(Long id) throws ProjectNotFoundException {
    return projectService.findProjectById(id);
  }

  @Override
  public UpdateProjectResponse updateProject(ProjectDTO projectDTO) {
    return projectService.updateProject(projectDTO);
  }

  @Override
  public UpdateProjectResponse addUserToProject(AddOrRemoveUserFromProjectRequest request)
      throws UserNotFoundException, ProjectNotFoundException {
    return projectService.addUserToProject(request);
  }

  @Override
  public UpdateProjectResponse deleteUserFromProject(AddOrRemoveUserFromProjectRequest request)
      throws UserNotFoundException, ProjectNotFoundException {
    return projectService.removeUserFromProject(request);
  }

  @Override
  public DeleteProjectResponce deleteProject(Long id) {
    return projectService.deleteProject(id);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    log.warn(e.getMessage());
    return new ResponseEntity<>(e, headers, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ProjectNotFoundException.class)
  public ResponseEntity<?> handleProjectNotFoundException(ProjectNotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    log.warn(e.getMessage());
    return new ResponseEntity<>(e, headers, HttpStatus.NOT_FOUND);
  }
}
