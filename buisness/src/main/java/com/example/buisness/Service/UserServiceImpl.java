package com.example.buisness.Service;

import com.example.buisness.DTO.CarDTO;
import com.example.buisness.DTO.DogDTO;
import com.example.buisness.DTO.UserDTO;
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

import com.example.buisness.Request.CreateDogForUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;
  private final CarMapper carMapper;
  private final DogMapper dogMapper;

  public UserServiceImpl(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      UserMapper userMapper,
      CarMapper carMapper,
      DogMapper dogMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
    this.carMapper = carMapper;
    this.dogMapper = dogMapper;
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
  public UserDTO findUserById(Long id) throws UserNotFoundException {
    UserEntity userEntity =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User Not Found", id));

    return userMapper.toDTO(userEntity);
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

    return userDTO;
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public void deleteAllUsers() {
    userRepository.deleteAll();
  }
}
