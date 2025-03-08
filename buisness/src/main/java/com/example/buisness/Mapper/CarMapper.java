package com.example.buisness.Mapper;

import com.example.buisness.DTO.CarDTO;
import com.example.db.Entity.CarEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
  CarDTO toDTO(CarEntity carEntity);

  CarEntity toEntity(CarDTO carDTO);

  List<CarDTO> toDTOList(List<CarEntity> carEntityList);
}
