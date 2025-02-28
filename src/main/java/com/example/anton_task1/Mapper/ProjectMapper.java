package com.example.anton_task1.Mapper;

import com.example.anton_task1.DTO.ProjectDTO;
import com.example.anton_task1.Entity.ProjectEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  ProjectDTO toDTO(ProjectEntity projectEntity);

  ProjectEntity toEntity(ProjectDTO projectDTO);

  List<ProjectDTO> toDTOs(List<ProjectEntity> projectEntities);
}
