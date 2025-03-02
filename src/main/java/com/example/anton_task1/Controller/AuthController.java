package com.example.anton_task1.Controller;

import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.LoginRequest;
import com.example.anton_task1.Request.RegisterRequest;
import com.example.anton_task1.Response.AuthenticationResponse;
import com.example.anton_task1.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws UserNotFoundException {
    return ResponseEntity.ok(authService.login(request));
  }
}
