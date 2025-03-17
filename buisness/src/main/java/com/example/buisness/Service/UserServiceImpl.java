package com.example.buisness.Service;

import com.example.buisness.DTO.*;
import com.example.db.Entity.CarEntity;
import com.example.db.Entity.DogEntity;
import com.example.db.Entity.UserEntity;
import com.example.buisness.Exception.UserExistsException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Mapper.CarMapper;
import com.example.buisness.Mapper.DogMapper;
import com.example.buisness.Mapper.UserMapper;
import com.example.db.Repository.UserRepository;
import com.example.buisness.Request.CreateCarForUserRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.example.buisness.Request.CreateDogForUserRequest;
import com.example.kafka.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final CarMapper carMapper;
  private final DogMapper dogMapper;
  private final KafkaSender kafkaSender;

  public UserServiceImpl(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      UserMapper userMapper,
      CarMapper carMapper,
      DogMapper dogMapper,
      KafkaSender kafkaSender) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
    this.carMapper = carMapper;
    this.dogMapper = dogMapper;
    this.kafkaSender = kafkaSender;
  }

  @Override
  public UserDTO createUser(UserDTO dao) throws UserExistsException {
    if (userRepository.existsByUsername(dao.getUsername())
        || userRepository.existsByEmail(dao.getEmail())) {
      System.out.println("Существует!");
      throw new UserExistsException("User already exists");
    } else {

      UserEntity user = userMapper.toEntity(dao);

      String encodedPass = passwordEncoder.encode(dao.getPassword());
      user.setPassword(encodedPass);

      user.setAuthorities("ROLE_USER");

      userRepository.save(user);

      UserDTO result = userMapper.toDTO(user);

      return result;
    }
  }

  @Override
  public UserAndCatDTO findUserById(Long id)
      throws UserNotFoundException, ExecutionException, InterruptedException, TimeoutException {
    UserEntity userEntity =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User Not Found", id));
    kafkaSender.sendLog("User found by id " + id);
    CompletableFuture<String> stringCompletableFuture =
        kafkaSender.sendFindUserCat(userEntity.getUsername());
    String catDTOString = stringCompletableFuture.get(9999, TimeUnit.SECONDS);
    log.info(catDTOString);

    return new UserAndCatDTO(userMapper.toDTO(userEntity), new CatDTO(2, "Вася"));
  }

  @Override
  public UserDTO updateUser(UserDTO dao) throws UserNotFoundException {
    UserEntity foundUser =
        userRepository
            .findById(dao.getId())
            .orElseThrow(() -> new UserNotFoundException("User Not Found", dao.getId()));

    foundUser.setEmail(dao.getEmail());
    foundUser.setPhone(dao.getPhone());
    foundUser.setUsername(dao.getUsername());
    String encodedPassword = passwordEncoder.encode(dao.getPassword());
    foundUser.setPassword(encodedPassword);
    userRepository.save(foundUser);
    UserDTO userDTO = userMapper.toDTO(foundUser);
    kafkaSender.sendLog("User updated successfully");

    return userDTO;
  }

  @Override
  public UserDTO createCarForUser(CreateCarForUserRequest request) throws UserNotFoundException {
    Long userId = request.getUserId();

    UserEntity foundEntity =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User Not Found", userId));

    List<CarEntity> userCars = foundEntity.getCars();
    CarDTO carDTO = request.getCarDTO();
    CarEntity carEntity = carMapper.toEntity(carDTO);
    userCars.add(carEntity);
    foundEntity.setCars(userCars);
    UserEntity updatedEntity = userRepository.save(foundEntity);
    UserDTO dto = userMapper.toDTO(updatedEntity);
    List<CarDTO> dtoCars = carMapper.toDTOList(userCars);
    dto.setCars(dtoCars);
    kafkaSender.sendLog("Car created successfully");

    return dto;
  }

  @Override
  public UserDTO createDogForUser(CreateDogForUserRequest request) throws UserNotFoundException {
    Long userId = request.getUserId();

    UserEntity foundEntity =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User Not Found", userId));

    DogDTO dogDTO = request.getDogDTO();
    DogEntity dogEntity = dogMapper.dtoToEntity(dogDTO);
    foundEntity.setDog(dogEntity);
    UserEntity updatedEntity = userRepository.save(foundEntity);
    UserDTO userDTO = userMapper.toDTO(updatedEntity);
    kafkaSender.sendLog("Dog created successfully");

    return userDTO;
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
    kafkaSender.sendLog("User deleted successfully");
  }

  @Override
  public void deleteAllUsers() {
    userRepository.deleteAll();
    kafkaSender.sendLog("All users deleted successfully");
  }
}
