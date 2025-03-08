package com.example.buisness.Mapper;

import com.example.buisness.DTO.ProjectDTO;
import com.example.db.Entity.ProjectEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  ProjectDTO toDTO(ProjectEntity projectEntity);

  ProjectEntity toEntity(ProjectDTO projectDTO);

  List<ProjectDTO> toDTOs(List<ProjectEntity> projectEntities);
}
