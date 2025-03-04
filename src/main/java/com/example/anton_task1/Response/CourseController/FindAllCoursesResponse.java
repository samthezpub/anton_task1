package com.example.anton_task1.Response.CourseController;

import com.example.anton_task1.DTO.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FindAllCoursesResponse {
    private String message;
    private List<CourseDTO> courses;
}
