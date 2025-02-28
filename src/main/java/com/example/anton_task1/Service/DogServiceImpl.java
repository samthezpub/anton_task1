package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.DogDTO;
import com.example.anton_task1.Entity.DogEntity;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.DogNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.DogMapper;
import com.example.anton_task1.Repository.DogRepository;
import com.example.anton_task1.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImpl implements DogService {
  private final DogRepository dogRepository;
  private final DogMapper dogMapper;
  private final UserRepository userRepository;

  public DogServiceImpl(
          DogRepository dogRepository, DogMapper dogMapper, UserRepository userRepository) {
    this.dogRepository = dogRepository;
    this.dogMapper = dogMapper;
    this.userRepository = userRepository;
  }

  @Override
  public DogDTO createDog(DogDTO dogDTO) {
    DogEntity dogEntity = dogMapper.dtoToEntity(dogDTO);

    DogEntity createdDog = dogRepository.save(dogEntity);

    DogDTO dogCreatedDTO = dogMapper.EntityToDto(createdDog);

    return dogCreatedDTO;
  }

  @Override
  public DogDTO findDogById(Long id) throws DogNotFoundException {
    DogEntity foundedDog =
        dogRepository.findById(id).orElseThrow(() -> new DogNotFoundException("Dog not found", id));

    DogDTO dogDTO = dogMapper.EntityToDto(foundedDog);

    return dogDTO;
  }

  @Override
  public DogDTO updateDog(DogDTO dogDTO) throws DogNotFoundException {
    DogEntity foundedDog =
        dogRepository
            .findById(dogDTO.getId())
            .orElseThrow(() -> new DogNotFoundException("Dog not found", dogDTO.getId()));

    foundedDog = dogMapper.dtoToEntity(dogDTO);

    DogEntity updatedDog = dogRepository.save(foundedDog);
    DogDTO dogUpdatedDTO = dogMapper.EntityToDto(updatedDog);

    return dogUpdatedDTO;
  }

  @Override
  public void deleteDog(Long id) throws UserNotFoundException {
    UserEntity userEntity =
        dogRepository
            .findUserByDogEntityId(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    userEntity.setDog(null);
    userRepository.save(userEntity);

    dogRepository.deleteById(id);
  }
}
