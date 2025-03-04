package com.example.anton_task1.Mapper;

import com.example.anton_task1.DTO.DogDTO;
import com.example.anton_task1.Entity.DogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DogMapper {
  DogEntity dtoToEntity(DogDTO dto);

  DogDTO EntityToDto(DogEntity dogEntity);
}
