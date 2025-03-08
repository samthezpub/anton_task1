package com.example.buisness.Mapper;

import com.example.buisness.DTO.CourseDTO;
import com.example.db.Entity.CourseEntity;
import com.example.buisness.Request.CreateCourseRequest;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  CourseDTO toDTO(CourseEntity courseEntity);

  CourseEntity toEntity(CourseDTO courseDTO);

  CourseEntity toEntity(CreateCourseRequest createCourseRequest);

  List<CourseDTO> toDTOList(List<CourseEntity> courseEntityList);
}
