package com.example.rest.Controller;

import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.LoginRequest;
import com.example.buisness.Request.RegisterRequest;
import com.example.buisness.Response.AuthenticationResponse;
import com.example.rest.Config.AuthService;
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
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request)
      throws UserNotFoundException {
    return ResponseEntity.ok(authService.login(request));
  }
}
