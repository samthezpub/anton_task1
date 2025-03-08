package com.example.buisness.Service;

import com.example.buisness.DTO.CarDTO;
import com.example.buisness.Exception.CarNotFoundException;
import com.example.buisness.Exception.UserNotFoundException;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
    CarDTO findCarById(Long id) throws CarNotFoundException;
    CarDTO updateCar(CarDTO carDTO) throws CarNotFoundException;
    void deleteCar(Long id) throws CarNotFoundException, UserNotFoundException;
}
