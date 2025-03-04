package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.Entity.ProjectEntity;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.ProjectNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.ProjectMapper;
import com.example.anton_task1.Mapper.UserMapper;
import com.example.anton_task1.Repository.ProjectRepository;
import com.example.anton_task1.Repository.UserRepository;
import com.example.anton_task1.Request.AddOrRemoveUserFromProjectRequest;
import com.example.anton_task1.Response.ProjectController.CreateProjectResponse;
import com.example.anton_task1.Response.ProjectController.DeleteProjectResponce;
import com.example.anton_task1.Response.ProjectController.FindProjectByIdResponse;
import com.example.anton_task1.Response.ProjectController.UpdateProjectResponse;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public ProjectServiceImpl(
      ProjectRepository projectRepository,
      ProjectMapper projectMapper,
      UserRepository userRepository,
      UserMapper userMapper) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public CreateProjectResponse createProject(ProjectDTO projectDTO) {
    ProjectEntity entity = projectRepository.save(projectMapper.toEntity(projectDTO));

    return CreateProjectResponse.builder()
        .message("Project successfully created")
        .project(projectMapper.toDTO(entity))
        .build();
  }

  @Override
  public FindProjectByIdResponse findProjectById(Long id) throws ProjectNotFoundException {
    ProjectDTO foundDto =
        projectMapper.toDTO(
            projectRepository
                .findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project Not Found", id)));

    return FindProjectByIdResponse.builder()
        .message("Project successfully found")
        .project(foundDto)
        .build();
  }

  @Override
  public UpdateProjectResponse updateProject(ProjectDTO projectDTO)
      throws ProjectNotFoundException {

    ProjectEntity projectEntity =
        projectRepository
            .findById(projectDTO.getId())
            .orElseThrow(
                () -> new ProjectNotFoundException("Project Not Found", projectDTO.getId()));
    projectEntity = projectMapper.toEntity(projectDTO);
    projectRepository.save(projectEntity);

    return UpdateProjectResponse.builder().project(projectMapper.toDTO(projectEntity)).message("Project successfully updated").build();
  }

  @Override
  public UpdateProjectResponse addUserToProject(AddOrRemoveUserFromProjectRequest request)
      throws UserNotFoundException, ProjectNotFoundException {
    UserEntity userEntity =
        userRepository
            .findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User Not Found", request.getUserId()));
    ProjectEntity projectEntity =
        projectRepository
            .findById(request.getProjectId())
            .orElseThrow(
                () -> new ProjectNotFoundException("Project Not Found", request.getProjectId()));

    userEntity.getProjects().add(projectEntity);
    userRepository.save(userEntity);

    return UpdateProjectResponse.builder()
        .message("User successfully added to project")
        .project(projectMapper.toDTO(projectEntity))
        .build();
  }

  @Override
  public UpdateProjectResponse removeUserFromProject(AddOrRemoveUserFromProjectRequest request)
      throws UserNotFoundException, ProjectNotFoundException {
    UserEntity userEntity =
        userRepository
            .findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User Not Found", request.getUserId()));
    ProjectEntity projectEntity =
        projectRepository
            .findById(request.getProjectId())
            .orElseThrow(
                () -> new ProjectNotFoundException("Project Not Found", request.getProjectId()));

    userEntity.getProjects().remove(projectEntity);
    userRepository.save(userEntity);

    return UpdateProjectResponse.builder()
        .message("User successfully added to project")
        .project(projectMapper.toDTO(projectEntity))
        .build();
  }

  @Override
  public DeleteProjectResponce deleteProject(Long id) {
    projectRepository.deleteById(id);
    return DeleteProjectResponce.builder().id(id).message("Project successfully deleted").build();
  }
}
