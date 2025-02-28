package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.UserDTO;
import com.example.anton_task1.Exception.UserExistsException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Request.CreateCarForUserRequest;
import com.example.anton_task1.Request.CreateDogForUserRequest;
import com.example.anton_task1.Response.UserController.CreateResponse;
import com.example.anton_task1.Response.UserController.DeleteResponse;
import com.example.anton_task1.Response.UserController.FindResponse;
import com.example.anton_task1.Response.UserController.UpdateResponse;
import com.example.anton_task1.Service.UserServiceImpl;
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

  @PostMapping("/createCar")
  public ResponseEntity<UpdateResponse> createCarForUser(@RequestBody CreateCarForUserRequest request) throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO updatedDTO = userService.createCarForUser(request);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(updatedDTO.getId());
    updateResponse.setMessage("Car created successfully");
    updateResponse.setUser(updatedDTO);

    ResponseEntity<UpdateResponse> response = new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
    return response;
  }

  @PostMapping("/createDog")
  public ResponseEntity<UpdateResponse> createDogForUser(@RequestBody CreateDogForUserRequest request) throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO updatedDTO = userService.createDogForUser(request);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(updatedDTO.getId());
    updateResponse.setMessage("Dog created successfully");
    updateResponse.setUser(updatedDTO);

    ResponseEntity<UpdateResponse> response = new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
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

  @DeleteMapping("/deleteAll")
  public ResponseEntity<DeleteResponse> deleteAllUser() {
    HttpHeaders headers = new HttpHeaders();

    userService.deleteAllUsers();

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setDeleted_id(0L);
    deleteResponse.setMessage("All Users is deleted");

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
