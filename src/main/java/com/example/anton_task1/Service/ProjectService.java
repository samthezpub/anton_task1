package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Exception.UserNotFoundException;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO findProjectById(Long id);
    ProjectDTO updateProject(ProjectDTO projectDTO);
    UserDTO addUserToProject(Long projectId, Long userId) throws UserNotFoundException;
    UserDTO removeUserFromProject(Long projectId, Long userId) throws UserNotFoundException;
    void deleteProject(Long id);
}
