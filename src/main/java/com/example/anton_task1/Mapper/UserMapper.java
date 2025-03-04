package com.example.anton_task1.Mapper;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserEntity toEntity(UserDTO dto);

  UserDTO toDTO(UserEntity userEntity);

  List<UserDTO> toDTOList(List<UserEntity> userEntities);
}
