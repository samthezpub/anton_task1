package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.DogDTO;
import com.example.anton_task1.Exception.DogNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Response.DogController.CreateResponse;
import com.example.anton_task1.Response.DogController.DeleteResponse;
import com.example.anton_task1.Response.DogController.FindResponse;
import com.example.anton_task1.Response.DogController.UpdateResponse;
import com.example.anton_task1.Service.DogServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dogs")
public class DogController {
  private final DogServiceImpl dogService;

  public DogController(DogServiceImpl dogService) {
    this.dogService = dogService;
  }

  @PostMapping("/create")
  public ResponseEntity<CreateResponse> createDog(@RequestBody DogDTO dogDTO) {
    HttpHeaders headers = new HttpHeaders();

    DogDTO createdDog = dogService.createDog(dogDTO);

    CreateResponse createResponse = new CreateResponse();
    createResponse.setMessage("Dog created successfully");
    createResponse.setId(createdDog.getId());
    createResponse.setDogDTO(createdDog);

    ResponseEntity<CreateResponse> response =
        new ResponseEntity<>(createResponse, headers, HttpStatus.CREATED);
    return response;
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<FindResponse> findDogById(@PathVariable("id") Long id)
      throws DogNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    DogDTO foundedDog = dogService.findDogById(id);

    FindResponse findResponse = new FindResponse();
    findResponse.setId(foundedDog.getId());
    findResponse.setMessage("Dog found successfully");
    findResponse.setDog(foundedDog);

    ResponseEntity<FindResponse> response =
        new ResponseEntity<>(findResponse, headers, HttpStatus.OK);
    return response;
  }

  @PostMapping("/update")
  public ResponseEntity<UpdateResponse> updateDog(@RequestBody DogDTO dogDTO)
      throws DogNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    DogDTO updatedDog = dogService.updateDog(dogDTO);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(updatedDog.getId());
    updateResponse.setMessage("Dog updated successfully");
    updateResponse.setDogDTO(updatedDog);

    ResponseEntity<UpdateResponse> response =
        new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
    return response;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DeleteResponse> deleteDogById(@PathVariable Long id) throws UserNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    dogService.deleteDog(id);

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setMessage("Dog deleted successfully");
    deleteResponse.setId(id);

    ResponseEntity<DeleteResponse> response =
        new ResponseEntity<>(deleteResponse, headers, HttpStatus.OK);
    return response;
  }

  @ExceptionHandler(DogNotFoundException.class)
  public ResponseEntity<?> handleDogNotFoundException(DogNotFoundException e) {
    HttpHeaders headers = new HttpHeaders();
    ResponseEntity<?> response = new ResponseEntity<>(e, headers, e.getCode());
    return response;
  }
}
