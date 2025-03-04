package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.CourseDTO;
import com.example.anton_task1.Exception.CourseNotFound;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.AddUserToCourseRequest;
import com.example.anton_task1.Request.CreateCourseRequest;
import com.example.anton_task1.Request.DeleteUserFromCourseRequest;
import com.example.anton_task1.Response.CourseController.*;
import org.springframework.web.bind.annotation.*;

public interface CourseController {
  @PostMapping("/create")
  CreateCourseResponse createCourse(@RequestBody CreateCourseRequest request);

  @GetMapping("/findByUser/{id}")
  FindCoursesByUserIdResponse findCoursesByUserId(@PathVariable("id") Long id)
      throws UserNotFoundException;

  @GetMapping("/")
  FindAllCoursesResponse findAllCourses();

  @GetMapping("/{id}")
  FindCourseByIdResponse findCourseById(@PathVariable("id") Long id) throws CourseNotFound;

  @PostMapping("/update")
  UpdateCourseResponse updateCourse(@RequestBody CourseDTO courseDTO) throws CourseNotFound;

  @PostMapping("/addUser")
  AddUserToCourseResponse addUserToCourse(@RequestBody AddUserToCourseRequest request)
      throws UserNotFoundException, CourseNotFound;

  @PostMapping("/deleteUser")
  DeleteUserFromCourseResponse deleteUserFromCourse(@RequestBody DeleteUserFromCourseRequest request) throws UserNotFoundException, CourseNotFound;

  @DeleteMapping("/delete/{id}")
  DeleteByIdResponse deleteCourseById(@PathVariable("id") Long id);
}
