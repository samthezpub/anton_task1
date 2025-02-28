package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.CreateCarForUserRequest;
import com.example.anton_task1.Request.CreateDogForUserRequest;

public interface UserService {
  UserDTO createUser(UserDTO user) throws UserExistsException;

  UserDTO findUserById(Long id) throws UserNotFoundException;

  UserDTO updateUser(UserDTO user) throws UserNotFoundException;

  UserDTO createCarForUser(CreateCarForUserRequest request) throws UserNotFoundException;

  UserDTO createDogForUser(CreateDogForUserRequest request) throws UserNotFoundException;

  void deleteUser(Long id);
  void deleteAllUsers();
}
