package com.example.anton_task1.Response;

import com.example.anton_task1.Entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateResponse {
  private Long id;
  private String message;
  private UserEntity user;
}
