package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.ProjectEntity;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.ProjectNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.ProjectMapper;
import com.example.anton_task1.Mapper.UserMapper;
import com.example.anton_task1.Repository.ProjectRepository;
import com.example.anton_task1.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository, UserMapper userMapper) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public ProjectDTO createProject(ProjectDTO projectDTO) {
    ProjectEntity projectEntity = projectMapper.toEntity(projectDTO);

    ProjectEntity createdEntity = projectRepository.save(projectEntity);
    ProjectDTO dto = projectMapper.toDTO(createdEntity);

    return dto;
  }

  @Override
  public ProjectDTO findProjectById(Long id) throws ProjectNotFoundException {
    ProjectDTO foundDto = projectMapper.toDTO(
            projectRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Project Not Found", id)));

    return foundDto;
  }

  @Override
  public ProjectDTO updateProject(ProjectDTO projectDTO) throws ProjectNotFoundException {

    ProjectEntity projectEntity = projectRepository
            .findById(projectDTO.getId())
            .orElseThrow(() -> new ProjectNotFoundException("Project Not Found", projectDTO.getId()));

    ProjectEntity entity = projectMapper.toEntity(projectDTO);

    projectEntity = entity;

    ProjectEntity updatedEntity = projectRepository.save(projectEntity);
    ProjectDTO updatedDto = projectMapper.toDTO(updatedEntity);

    return updatedDto;
  }

  @Override
  public UserDTO addUserToProject(Long projectId, Long userId) throws UserNotFoundException, ProjectNotFoundException {
    UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found", userId));
    ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project Not Found", projectId));

    userEntity.getProjects().add(projectEntity);
    UserEntity updatedUser = userRepository.save(userEntity);
    UserDTO dto = userMapper.toDTO(updatedUser);

    return dto;
  }

  @Override
  public UserDTO removeUserFromProject(Long projectId, Long userId) throws UserNotFoundException, ProjectNotFoundException {
    UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found", userId));
    ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project Not Found", projectId));

    userEntity.getProjects().remove(projectEntity);
    UserEntity updatedUser = userRepository.save(userEntity);
    UserDTO dto = userMapper.toDTO(updatedUser);

    return dto;
  }

  @Override
  public void deleteProject(Long id) {
    ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project Not Found", id));


  }
}
