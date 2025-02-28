package com.example.anton_task1.Mapper;

import com.example.anton_task1.DTO.CarDTO;
import com.example.anton_task1.Entity.CarEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(CarEntity carEntity);
    CarEntity toEntity(CarDTO carDTO);

    List<CarDTO> toDTOList(List<CarEntity> carEntityList);
}
