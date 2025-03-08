package com.example.buisness.Service;

import com.example.buisness.DTO.UserDTO;
import com.example.buisness.Exception.UserExistsException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.CreateCarForUserRequest;
import com.example.buisness.Request.CreateDogForUserRequest;

public interface UserService {
  UserDTO createUser(UserDTO user) throws UserExistsException;

  UserDTO findUserById(Long id) throws UserNotFoundException;

  UserDTO updateUser(UserDTO user) throws UserNotFoundException;

  UserDTO createCarForUser(CreateCarForUserRequest request) throws UserNotFoundException;

  UserDTO createDogForUser(CreateDogForUserRequest request) throws UserNotFoundException;

  void deleteUser(Long id);
  void deleteAllUsers();
}
