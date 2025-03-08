package com.example.buisness.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;

  private String email;
  private String phone;

  private String username;
  private String password;

  private DogDTO dog;
  private List<CarDTO> cars;
  private List<CourseDTO> courses;
}
