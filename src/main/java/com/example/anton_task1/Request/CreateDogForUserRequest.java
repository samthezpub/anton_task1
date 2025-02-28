package com.example.anton_task1.Request;

import com.example.anton_task1.DTO.DogDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDogForUserRequest {
    private Long userId;
    private DogDTO dogDTO;
}
