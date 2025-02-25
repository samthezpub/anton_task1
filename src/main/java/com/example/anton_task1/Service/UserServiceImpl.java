package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserEntity createUser(UserDTO dao) {
        UserEntity user = new UserEntity();
        if (isUserExists(dao.getUsername()) || isUserExistsByEmail(dao.getEmail())){
            System.out.println("Существует!");
            throw new UserExistsException("Пользователь с таким username существует!");
        }

        user.setEmail(dao.getEmail());
        user.setPhone(dao.getPhone());
        user.setUsername(dao.getUsername());

        String encodedPass = passwordEncoder.encode(dao.getPassword());
        user.setPassword(encodedPass);

        user.setAuthorities("ROLE_USER");

        userRepository.save(user);

        return user;
    }

    @Override
    public UserEntity findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с id" + id + " не найден!"));
    }

    @Override
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity updateUser(UserDTO dao) throws UserNotFoundException {
        UserEntity foundUser = userRepository.findById(dao.getId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id" + dao.getId() + " не найден!"));

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
