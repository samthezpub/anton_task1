package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.AddOrRemoveUserFromProjectRequest;
import com.example.anton_task1.Response.ProjectController.CreateProjectResponse;
import com.example.anton_task1.Response.ProjectController.DeleteProjectResponce;
import com.example.anton_task1.Response.ProjectController.FindProjectByIdResponse;
import com.example.anton_task1.Response.ProjectController.UpdateProjectResponse;
import org.springframework.web.bind.annotation.*;

public interface ProjectController {

  @PostMapping("/create")
  CreateProjectResponse createProject(@RequestBody ProjectDTO projectDTO);

  @GetMapping("/find/{id}")
  FindProjectByIdResponse findProject(@PathVariable("id") Long id);

  @PostMapping("/update")
  UpdateProjectResponse updateProject(@RequestBody ProjectDTO projectDTO);

  @PostMapping("/addUser")
  UpdateProjectResponse addUserToProject(@RequestBody AddOrRemoveUserFromProjectRequest request)
      throws UserNotFoundException;

  @PostMapping("/deleteUser")
  UpdateProjectResponse deleteUserFromProject(
      @RequestBody AddOrRemoveUserFromProjectRequest request) throws UserNotFoundException;

  @DeleteMapping("/delete/{id}")
  DeleteProjectResponce deleteProject(@PathVariable("id") Long id);
}
