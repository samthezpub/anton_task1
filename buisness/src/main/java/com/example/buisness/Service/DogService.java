package com.example.buisness.Service;

import com.example.buisness.DTO.DogDTO;
import com.example.buisness.Exception.DogNotFoundException;
import com.example.buisness.Exception.UserNotFoundException;

public interface DogService {
    DogDTO createDog(DogDTO dogDTO);
    DogDTO findDogById(Long id) throws DogNotFoundException;
    DogDTO updateDog(DogDTO dogDTO) throws DogNotFoundException;
    void deleteDog(Long id) throws UserNotFoundException;

}
