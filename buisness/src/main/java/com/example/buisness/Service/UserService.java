package com.example.buisness.Service;

import com.example.buisness.DTO.UserAndCatDTO;
import com.example.buisness.DTO.UserDTO;
import com.example.buisness.Exception.UserExistsException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.CreateCarForUserRequest;
import com.example.buisness.Request.CreateDogForUserRequest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface UserService {
  UserDTO createUser(UserDTO user) throws UserExistsException;

  UserAndCatDTO findUserById(Long id) throws UserNotFoundException, ExecutionException, InterruptedException, TimeoutException;

  UserDTO updateUser(UserDTO user) throws UserNotFoundException;

  UserDTO createCarForUser(CreateCarForUserRequest request) throws UserNotFoundException;

  UserDTO createDogForUser(CreateDogForUserRequest request) throws UserNotFoundException;

  void deleteUser(Long id);
  void deleteAllUsers();
}
