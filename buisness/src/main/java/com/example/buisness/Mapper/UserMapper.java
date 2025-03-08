package com.example.buisness.Mapper;

import com.example.buisness.DTO.UserDTO;
import com.example.db.Entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserEntity toEntity(UserDTO dto);

  UserDTO toDTO(UserEntity userEntity);

  List<UserDTO> toDTOList(List<UserEntity> userEntities);
}
