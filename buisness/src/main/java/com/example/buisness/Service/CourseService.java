package com.example.buisness.Service;

import com.example.buisness.DTO.CourseDTO;
import com.example.buisness.Exception.CourseNotFound;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.AddUserToCourseRequest;
import com.example.buisness.Request.CreateCourseRequest;
import com.example.buisness.Request.DeleteUserFromCourseRequest;
import com.example.buisness.Response.CourseController.*;

public interface CourseService {
  CreateCourseResponse createCourse(CreateCourseRequest courseDTO);

  FindAllCoursesResponse findAllCourses();

  FindCoursesByUserIdResponse findCoursesByUserId(Long userId) throws UserNotFoundException;

  FindCourseByIdResponse findCourseById(Long id) throws CourseNotFound;
  UpdateCourseResponse updateCourse(CourseDTO courseDTO) throws CourseNotFound;
  AddUserToCourseResponse addUserToCourse(AddUserToCourseRequest addUserToCourseRequest) throws CourseNotFound, UserNotFoundException;
  DeleteUserFromCourseResponse deleteUserFromCourse(DeleteUserFromCourseRequest request) throws CourseNotFound, UserNotFoundException;
  DeleteByIdResponse deleteCourseById(Long id);
}
