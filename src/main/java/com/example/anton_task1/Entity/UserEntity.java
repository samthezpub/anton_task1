package com.example.anton_task1.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;
  private String password;

  private String authorities;

  private String email;
  private String phone;


  @Override
  public String toString() {
    return "UserEntity{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + Arrays.stream(password.split("")).map(s -> s = "*") + '\'' +
            ", authorities='" + authorities + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            '}';
  }
}
