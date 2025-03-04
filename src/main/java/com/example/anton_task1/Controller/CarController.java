package com.example.anton_task1.Controller;

import com.example.anton_task1.DTO.CarDTO;
import com.example.anton_task1.Exception.CarNotFoundException;
import com.example.anton_task1.Exception.UserNotFoundException;
import com.example.anton_task1.Response.CarController.CreateResponse;
import com.example.anton_task1.Response.CarController.FindResponse;
import com.example.anton_task1.Response.CarController.UpdateResponse;
import com.example.anton_task1.Response.DogController.DeleteResponse;
import com.example.anton_task1.Service.CarServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
  private final CarServiceImpl carService;

  public CarController(CarServiceImpl carService) {
    this.carService = carService;
  }

  @PostMapping("/create")
  public ResponseEntity<CreateResponse> createCar(@RequestBody CarDTO carDTO) {
    HttpHeaders headers = new HttpHeaders();

    CarDTO createdDTO = carService.createCar(carDTO);

    CreateResponse createResponse = new CreateResponse();
    createResponse.setId(createdDTO.getId());
    createResponse.setMessage("Car successfully created");
    createResponse.setCarDTO(createdDTO);

    ResponseEntity<CreateResponse> response =
        new ResponseEntity<>(createResponse, headers, HttpStatus.CREATED);
    return response;
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<FindResponse> findCarById(@PathVariable("id") Long id)
      throws CarNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    CarDTO foundedDTO = carService.findCarById(id);

    FindResponse findResponse = new FindResponse();
    findResponse.setId(foundedDTO.getId());
    findResponse.setMessage("Car successfully found");
    findResponse.setCar(foundedDTO);

    ResponseEntity<FindResponse> response =
        new ResponseEntity<>(findResponse, headers, HttpStatus.OK);
    return response;
  }

  @PostMapping("/update")
  public ResponseEntity<UpdateResponse> updateCar(@RequestBody CarDTO carDTO)
      throws CarNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    CarDTO updatedDTO = carService.updateCar(carDTO);

    UpdateResponse updateResponse = new UpdateResponse();
    updateResponse.setId(carDTO.getId());
    updateResponse.setMessage("Car successfully updated");
    updateResponse.setCarDTO(updatedDTO);

    ResponseEntity<UpdateResponse> response =
        new ResponseEntity<>(updateResponse, headers, HttpStatus.OK);
    return response;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DeleteResponse> deleteCar(@PathVariable("id") Long id)
      throws UserNotFoundException, CarNotFoundException {
    HttpHeaders headers = new HttpHeaders();

    carService.deleteCar(id);
    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setId(id);
    deleteResponse.setMessage("Car successfully deleted");

    ResponseEntity<DeleteResponse> response =
        new ResponseEntity<>(deleteResponse, headers, HttpStatus.OK);
    return response;
  }
}
