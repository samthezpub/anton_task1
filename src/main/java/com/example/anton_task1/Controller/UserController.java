package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Entity.UserEntity;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Response.CreateResponse;
import com.example.anton_task1.Response.DeleteResponse;
import com.example.anton_task1.Response.FindResponse;
import com.example.anton_task1.Response.UpdateResponse;
import com.example.anton_task1.Service.UserServiceImpl;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/hello")
  public ResponseEntity<?> hello() {
    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<CreateResponse> createUser(@RequestBody UserDTO user)
      throws UserExistsException {
    HttpHeaders headers = new HttpHeaders();
    CreateResponse createResponse = new CreateResponse();

    UserDTO createdUser = userService.createUser(user);
    createResponse.setId(createdUser.getId());
    createResponse.setMessage("Успешно создан!");
    createResponse.setUser(createdUser);

    ResponseEntity<CreateResponse> response =
        new ResponseEntity<>(createResponse, headers, HttpStatus.CREATED);

    return response;
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<FindResponse> getUserById(@PathVariable Long id)
      throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO userDTO = userService.findUserById(id);

    FindResponse findResponse = new FindResponse();
    findResponse.setId(id);
    findResponse.setMessage("Success founded user");
    findResponse.setUser(userDTO);

    ResponseEntity<FindResponse> response =
        new ResponseEntity<>(findResponse, headers, HttpStatus.OK);

    return response;
  }

  @PostMapping("/update")
  public ResponseEntity<UpdateResponse> updateUser(@RequestBody UserDTO user)
      throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO userDTO = userService.updateUser(user);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(userDTO.getId());
    updateResponse.setMessage("User updated successfully");
    updateResponse.setUser(userDTO);

    ResponseEntity<UpdateResponse> response =
        new ResponseEntity<UpdateResponse>(updateResponse, headers, HttpStatus.OK);

    return response;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DeleteResponse> deleteUserById(@PathVariable Long id) {
    userService.deleteUser(id);

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setDeleted_id(id);
    deleteResponse.setMessage("User is deleted");

    HttpHeaders headers = new HttpHeaders();

    ResponseEntity<DeleteResponse> response =
        new ResponseEntity<>(deleteResponse, headers, HttpStatus.OK);

    return response;
  }

  @ExceptionHandler(UserExistsException.class)
  public ResponseEntity<?> handleException(UserExistsException e) {
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<?> response = new ResponseEntity<>(e, headers, e.getCode());
    return response;
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleException(UserNotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<?> response = new ResponseEntity<>(e, headers, e.getCode());
    return response;
  }
}
