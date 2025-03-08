package com.example.rest.Controller;

import com.example.buisness.DTO.CourseDTO;
import com.example.buisness.Exception.CourseNotFound;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.AddUserToCourseRequest;
import com.example.buisness.Request.CreateCourseRequest;
import com.example.buisness.Request.DeleteUserFromCourseRequest;
import com.example.buisness.Response.CourseController.*;
import com.example.buisness.Service.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseControllerImpl implements CourseController {
  private final CourseServiceImpl courseService;

  @Override
  public CreateCourseResponse createCourse(CreateCourseRequest request) {
    return courseService.createCourse(request);
  }

  @Override
  public FindCoursesByUserIdResponse findCoursesByUserId(Long id) throws UserNotFoundException {
    return courseService.findCoursesByUserId(id);
  }

  @Override
  public FindAllCoursesResponse findAllCourses() {
    return courseService.findAllCourses();
  }

  @Override
  public FindCourseByIdResponse findCourseById(Long id) throws CourseNotFound {
    return courseService.findCourseById(id);
  }

  @Override
  public UpdateCourseResponse updateCourse(CourseDTO courseDTO) throws CourseNotFound {
    return courseService.updateCourse(courseDTO);
  }

  @Override
  public AddUserToCourseResponse addUserToCourse(AddUserToCourseRequest request)
      throws UserNotFoundException, CourseNotFound {
    return courseService.addUserToCourse(request);
  }

  @Override
  public DeleteUserFromCourseResponse deleteUserFromCourse(DeleteUserFromCourseRequest request)
      throws UserNotFoundException, CourseNotFound {
    return courseService.deleteUserFromCourse(request);
  }

  @Override
  public DeleteByIdResponse deleteCourseById(Long id) {
    return courseService.deleteCourseById(id);
  }

  @ExceptionHandler(CourseNotFound.class)
  public ResponseEntity<?> handleCourseNotFoundException(CourseNotFound e) {
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<?> response = new ResponseEntity<>(e, headers, e.getCode());
    log.warn(e.getMessage());
    return response;
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<?> response = new ResponseEntity<>(e, headers, e.getCode());
    log.warn(e.getMessage());
    return response;
  }
}
