package com.example.rest.Controller;

import com.example.buisness.DTO.ProjectDTO;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.AddOrRemoveUserFromProjectRequest;
import com.example.buisness.Response.ProjectController.CreateProjectResponse;
import com.example.buisness.Response.ProjectController.DeleteProjectResponce;
import com.example.buisness.Response.ProjectController.FindProjectByIdResponse;
import com.example.buisness.Response.ProjectController.UpdateProjectResponse;
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
