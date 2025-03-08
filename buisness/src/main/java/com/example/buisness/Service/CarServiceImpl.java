package com.example.buisness.Service;

import com.example.buisness.DTO.CarDTO;
import com.example.db.Entity.CarEntity;
import com.example.buisness.Exception.CarNotFoundException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Mapper.CarMapper;
import com.example.db.Repository.CarRepository;
import com.example.db.Repository.UserRepository;
import org.springframework.stereotype.Service;

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
