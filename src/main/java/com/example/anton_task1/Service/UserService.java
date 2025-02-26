package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Response.CreateResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
  UserDTO createUser(UserDTO user) throws UserExistsException;

  UserEntity findUserById(Long id) throws UserNotFoundException;

  UserEntity updateUser(UserDTO user) throws UserNotFoundException;

  void deleteUser(Long id);
}
