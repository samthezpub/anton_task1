package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.UserMapper;
import com.example.anton_task1.Repository.UserRepository;
import com.example.anton_task1.Response.CreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  public UserServiceImpl(
      UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
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
  public UserEntity findUserById(Long id) throws UserNotFoundException {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User Not Found", id));
  }

  @Override
  public UserEntity updateUser(UserDTO dao) throws UserNotFoundException {
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

    return foundUser;
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
