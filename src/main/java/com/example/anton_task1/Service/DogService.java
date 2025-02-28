package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.DogDTO;
import com.example.anton_task1.Exception.DogNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;

public interface DogService {
    DogDTO createDog(DogDTO dogDTO);
    DogDTO findDogById(Long id) throws DogNotFoundException;
    DogDTO updateDog(DogDTO dogDTO) throws DogNotFoundException;
    void deleteDog(Long id) throws UserNotFoundException;

}
