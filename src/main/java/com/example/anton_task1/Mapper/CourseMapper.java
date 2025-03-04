package com.example.anton_task1.Mapper;

import com.example.anton_task1.DTO.CourseDTO;
import com.example.anton_task1.Entity.CourseEntity;
import java.util.List;

import com.example.anton_task1.Request.CreateCourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  CourseDTO toDTO(CourseEntity courseEntity);

  CourseEntity toEntity(CourseDTO courseDTO);
  CourseEntity toEntity(CreateCourseRequest createCourseRequest);

  List<CourseDTO> toDTOList(List<CourseEntity> courseEntityList);
}
