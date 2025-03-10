package com.example.rest.Config;

import com.example.db.Entity.UserEntity;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.db.Repository.UserRepository;
import com.example.buisness.Request.LoginRequest;
import com.example.buisness.Request.RegisterRequest;
import com.example.buisness.Response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(request.getUsername());

    String encodedPass = passwordEncoder.encode(request.getPassword());
    userEntity.setPassword(encodedPass);
    userEntity.setAuthorities("ROLE_USER");
    userRepository.save(userEntity);

    MyUserPrincipal userPrincipal = new MyUserPrincipal(userEntity);

    String token = jwtService.generateToken(userPrincipal);

    return new AuthenticationResponse(token);
  }

  public AuthenticationResponse login(LoginRequest request) throws UserNotFoundException {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    var userEntity = userRepository.findByUsername(request.getUsername());
    if (userEntity == null) {
      throw new UserNotFoundException("User not found");
    }
    MyUserPrincipal userPrincipal = new MyUserPrincipal(userEntity);

    String token = jwtService.generateToken(userPrincipal);

    return new AuthenticationResponse(token);

  }
}
