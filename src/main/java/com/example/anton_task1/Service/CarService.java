package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.CarDTO;
import com.example.anton_task1.Exception.CarNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
    CarDTO findCarById(Long id) throws CarNotFoundException;
    CarDTO updateCar(CarDTO carDTO) throws CarNotFoundException;
    void deleteCar(Long id) throws CarNotFoundException, UserNotFoundException;
}
