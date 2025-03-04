package com.example.anton_task1.Service;

import com.example.anton_task1.DTO.CourseDTO;
import com.example.anton_task1.Entity.CourseEntity;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.CourseNotFound;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Mapper.CourseMapper;
import com.example.anton_task1.Repository.CourseRepository;
import com.example.anton_task1.Repository.UserRepository;
import com.example.anton_task1.Request.AddUserToCourseRequest;
import com.example.anton_task1.Request.CreateCourseRequest;
import com.example.anton_task1.Request.DeleteUserFromCourseRequest;
import com.example.anton_task1.Response.CourseController.*;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final UserRepository userRepository;
  private final CourseMapper courseMapper;

  @Override
  public CreateCourseResponse createCourse(CreateCourseRequest request) {
    CourseEntity entity = new CourseEntity();
    entity.setName(request.getName());
    entity.setDescription(request.getDescription());

    CourseEntity createdCourse = courseRepository.save(entity);

    log.info("Курс создан успешно!");

    return CreateCourseResponse.builder()
        .message("Course created successfully")
        .course(courseMapper.toDTO(createdCourse))
        .build();
  }

  @Override
  public FindAllCoursesResponse findAllCourses() {
    List<CourseDTO> courseDTOS =
        courseRepository.findAll().stream().map(courseMapper::toDTO).toList();

    log.info("Все курсы найдены!");

    FindAllCoursesResponse response =
        FindAllCoursesResponse.builder()
            .message("Founded successfully")
            .courses(courseDTOS)
            .build();

    return response;
  }

  @Override
  public FindCoursesByUserIdResponse findCoursesByUserId(Long userId) throws UserNotFoundException {
    List<CourseDTO> courseDTOS =
        userRepository
            .findAllCoursesByUserId(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found", userId))
            .stream()
            .map(courseMapper::toDTO)
            .collect(Collectors.toList());

    FindCoursesByUserIdResponse response =
        FindCoursesByUserIdResponse.builder()
            .message("Founded courses successfully by user")
            .courses(courseDTOS)
            .build();

    log.info("Курсы по пользователю найдены");

    return response;
  }

  @Override
  public FindCourseByIdResponse findCourseById(Long id) throws CourseNotFound {
    CourseDTO dto =
        courseMapper.toDTO(
            courseRepository
                .findById(id)
                .orElseThrow(() -> new CourseNotFound("Course not found!", id)));

    FindCourseByIdResponse response =
        FindCourseByIdResponse.builder().message("Course found successfully").course(dto).build();

    log.info("Курс по id найден");
    return response;
  }

  @Override
  public UpdateCourseResponse updateCourse(CourseDTO courseDTO) throws CourseNotFound {
    if (!courseRepository.existsById(courseDTO.getId())) {
      throw new CourseNotFound("Course not found!", courseDTO.getId());
    }

    CourseDTO dto = courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));

    UpdateCourseResponse response =
        UpdateCourseResponse.builder().message("Course updated successfully").course(dto).build();

    log.info("Курс успешно обновлён");

    return response;
  }

  @Override
  public AddUserToCourseResponse addUserToCourse(AddUserToCourseRequest addUserToCourseRequest)
      throws CourseNotFound, UserNotFoundException {

    CourseEntity courseEntity =
        courseRepository
            .findById(addUserToCourseRequest.getCourseId())
            .orElseThrow(
                () ->
                    new CourseNotFound("Course not found!", addUserToCourseRequest.getCourseId()));

    UserEntity userEntity =
        userRepository
            .findById(addUserToCourseRequest.getUserId())
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        "User not found", addUserToCourseRequest.getUserId()));

    userEntity.getCourses().add(courseEntity);

    userRepository.save(userEntity);

    log.info("К курсу успешно добавлен пользователь");

    return AddUserToCourseResponse.builder()
        .message("User successfully added to course")
        .course(courseMapper.toDTO(courseEntity))
        .build();
  }

  @Override
  public DeleteUserFromCourseResponse deleteUserFromCourse(DeleteUserFromCourseRequest request)
      throws CourseNotFound, UserNotFoundException {
    CourseEntity courseEntity =
        courseRepository
            .findById(request.getCourseId())
            .orElseThrow(() -> new CourseNotFound("Course not found!", request.getCourseId()));

    UserEntity userEntity =
        userRepository
            .findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found", request.getUserId()));

    userEntity.getCourses().remove(courseEntity);

    userRepository.save(userEntity);
    return DeleteUserFromCourseResponse.builder()
        .message("User successfully deleted from course")
        .course(courseMapper.toDTO(courseEntity))
        .build();
  }

  @Override
  public DeleteByIdResponse deleteCourseById(Long id) {
    courseRepository.deleteById(id);
    log.info("Курс успешно удалён");
    return DeleteByIdResponse.builder().id(id).message("Course deleted successfully").build();
  }
}
