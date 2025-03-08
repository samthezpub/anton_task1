package com.example.buisness.Mapper;

import com.example.buisness.DTO.DogDTO;
import com.example.db.Entity.DogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DogMapper {
  DogEntity dtoToEntity(DogDTO dto);

  DogDTO EntityToDto(DogEntity dogEntity);
}
