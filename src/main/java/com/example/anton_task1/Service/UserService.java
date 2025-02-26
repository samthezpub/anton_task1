package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;

public interface UserService {
  UserDTO createUser(UserDTO user) throws UserExistsException;

  UserDTO findUserById(Long id) throws UserNotFoundException;

  UserDTO updateUser(UserDTO user) throws UserNotFoundException;

  void deleteUser(Long id);
}
