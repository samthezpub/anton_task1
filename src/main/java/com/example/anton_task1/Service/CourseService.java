package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.CourseDTO;
import com.example.anton_task1.Exception.CourseNotFound;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.AddUserToCourseRequest;
import com.example.anton_task1.Request.CreateCourseRequest;
import com.example.anton_task1.Request.DeleteUserFromCourseRequest;
import com.example.anton_task1.Response.CourseController.*;
import org.springframework.web.bind.annotation.RequestBody;

public interface CourseService {
  CreateCourseResponse createCourse(CreateCourseRequest courseDTO);

  FindAllCoursesResponse findAllCourses();

  FindCoursesByUserIdResponse findCoursesByUserId(Long userId) throws UserNotFoundException;

  FindCourseByIdResponse findCourseById(Long id) throws CourseNotFound;
  UpdateCourseResponse updateCourse(CourseDTO courseDTO) throws CourseNotFound;
  AddUserToCourseResponse addUserToCourse(AddUserToCourseRequest addUserToCourseRequest) throws CourseNotFound, UserNotFoundException;
  DeleteUserFromCourseResponse deleteUserFromCourse(@RequestBody DeleteUserFromCourseRequest request) throws CourseNotFound, UserNotFoundException;
  DeleteByIdResponse deleteCourseById(Long id);
}
