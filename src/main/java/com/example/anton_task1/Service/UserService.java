package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.UserNotFoundException;

public interface UserService {
    UserEntity createUser(UserDTO user);
    UserEntity findUserById(Long id) throws UserNotFoundException;
    UserEntity updateUser(UserDTO user) throws UserNotFoundException;
    void deleteUser(Long id);
}
