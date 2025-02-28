package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.CarDTO;
import com.example.anton_task1.Entity.CarEntity;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.CarNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.CarMapper;
import com.example.anton_task1.Repository.CarRepository;
import com.example.anton_task1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;
  private final CarMapper carMapper;
  private final UserRepository userRepository;

  public CarServiceImpl(
      CarRepository carRepository, CarMapper carMapper, UserRepository userRepository) {
    this.carRepository = carRepository;
    this.carMapper = carMapper;
    this.userRepository = userRepository;
  }

  @Override
  public CarDTO createCar(CarDTO carDTO) {
    CarEntity carEntity = carMapper.toEntity(carDTO);

    CarDTO createdDTO = carMapper.toDTO(carEntity);

    return createdDTO;
  }

  @Override
  public CarDTO findCarById(Long id) throws CarNotFoundException {
    CarEntity carEntity =
        carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found", id));
    CarDTO foundedDTO = carMapper.toDTO(carEntity);

    return foundedDTO;
  }

  @Override
  public CarDTO updateCar(CarDTO carDTO) throws CarNotFoundException {
    CarEntity foundEntity =
        carRepository
            .findById(carDTO.getId())
            .orElseThrow(() -> new CarNotFoundException("Car not found", carDTO.getId()));

    foundEntity = carMapper.toEntity(carDTO);

    CarDTO updatedDTO = carMapper.toDTO(carRepository.save(foundEntity));
    return updatedDTO;
  }

  @Override
  public void deleteCar(Long id) throws CarNotFoundException, UserNotFoundException {
//    CarEntity foundedCar = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException("Car not found!", id));
//    UserEntity userEntity = carRepository.findUserEntityByCar(foundedCar).orElseThrow(()-> new UserNotFoundException("User not found!"));
//
//    List<CarEntity> userCars = userEntity.getCars();
//    userCars.remove(foundedCar);
//
//    userEntity.setCars(userCars);
//
//    userRepository.save(userEntity);


    carRepository.deleteById(id);
  }
}
