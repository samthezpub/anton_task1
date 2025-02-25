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
  public ResponseEntity<CreateResponse> createUser(@RequestBody UserDTO user) {
    HttpHeaders headers = new HttpHeaders();

    try {

      UserEntity createdUser = userService.createUser(user);

      CreateResponse createResponse = new CreateResponse();
      createResponse.setId(user.getId());
      createResponse.setMessage("User created successfully");
      createResponse.setUser(createdUser);

      ResponseEntity<CreateResponse> response =
          new ResponseEntity<>(createResponse, headers, HttpStatus.CREATED);

      return response;

    } catch (UserExistsException e) {
      CreateResponse createResponse = new CreateResponse();
      createResponse.setId(null);
      createResponse.setMessage("User already exists");
      createResponse.setUser(null);

      ResponseEntity<CreateResponse> response =
          new ResponseEntity<>(createResponse, headers, HttpStatus.CONFLICT);

      return response;
    }
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<FindResponse> getUserById(@PathVariable Long id) {
    HttpHeaders headers = new HttpHeaders();
    try {
      UserEntity userEntity = userService.findUserById(id);

      FindResponse findResponse = new FindResponse();
      findResponse.setId(id);
      findResponse.setMessage("Success founded user");
      findResponse.setUser(userEntity);

      ResponseEntity<FindResponse> response =
          new ResponseEntity<>(findResponse, headers, HttpStatus.OK);

      return response;
    } catch (UserNotFoundException e) {
      FindResponse findResponse = new FindResponse();
      findResponse.setId(id);
      findResponse.setMessage("Success founded user");
      findResponse.setUser(null);

      ResponseEntity<FindResponse> response =
          new ResponseEntity<>(findResponse, headers, HttpStatus.NOT_FOUND);

      return response;
    }
  }

  @PostMapping("/update")
  public ResponseEntity<UpdateResponse> updateUser(@RequestBody UserDTO user) {
    HttpHeaders headers = new HttpHeaders();

    try {
      UserEntity userEntity = userService.updateUser(user);

      UpdateResponse updateResponse = new UpdateResponse();
      updateResponse.setId(userEntity.getId());
      updateResponse.setMessage("User updated successfully");
      updateResponse.setUser(userEntity);

      ResponseEntity<UpdateResponse> response =
          new ResponseEntity<UpdateResponse>(updateResponse, headers, HttpStatus.OK);

      return response;
    } catch (UserNotFoundException e) {
      UpdateResponse updateResponse = new UpdateResponse();
      updateResponse.setId(user.getId());
      updateResponse.setMessage("User not updated");

      ResponseEntity<UpdateResponse> response =
          new ResponseEntity<UpdateResponse>(updateResponse, headers, HttpStatus.BAD_REQUEST);

      return response;
    }
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
}
