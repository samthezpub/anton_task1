package com.example.rest.Controller;

import com.example.buisness.DTO.UserAndCatDTO;
import com.example.buisness.DTO.UserDTO;
import com.example.buisness.Exception.UserExistsException;
import com.example.buisness.Exception.UserNotFoundException;
import com.example.buisness.Request.CreateCarForUserRequest;
import com.example.buisness.Request.CreateDogForUserRequest;
import com.example.buisness.Response.UserController.CreateResponse;
import com.example.buisness.Response.UserController.DeleteResponse;
import com.example.buisness.Response.UserController.FindResponse;
import com.example.buisness.Response.UserController.UpdateResponse;
import com.example.buisness.Service.UserServiceImpl;
import com.example.rest.Client.CatClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserServiceImpl userService;
  private final CatClient catClient;

  public UserController(UserServiceImpl userService, CatClient catClient) {
    this.userService = userService;
    this.catClient = catClient;
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
      throws UserNotFoundException, ExecutionException, InterruptedException, TimeoutException, JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();

    UserAndCatDTO userById = userService.findUserById(id);

    FindResponse findResponse = new FindResponse();
    findResponse.setId(id);
    findResponse.setMessage("Success founded user");
    findResponse.setDto(userById);

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
  public ResponseEntity<UpdateResponse> createCarForUser(
      @RequestBody CreateCarForUserRequest request) throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO updatedDTO = userService.createCarForUser(request);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(updatedDTO.getId());
    updateResponse.setMessage("Car created successfully");
    updateResponse.setUser(updatedDTO);

    ResponseEntity<UpdateResponse> response =
        new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
    return response;
  }

  @PostMapping("/createDog")
  public ResponseEntity<UpdateResponse> createDogForUser(
      @RequestBody CreateDogForUserRequest request) throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    UserDTO updatedDTO = userService.createDogForUser(request);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(updatedDTO.getId());
    updateResponse.setMessage("Dog created successfully");
    updateResponse.setUser(updatedDTO);

    ResponseEntity<UpdateResponse> response =
        new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
    return response;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DeleteResponse> deleteUserById(@PathVariable Long id)
      throws UserNotFoundException, ExecutionException, InterruptedException, TimeoutException, JsonProcessingException {

    catClient.deleteCatByName(userService.findUserById(id).getUser().getUsername());
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
