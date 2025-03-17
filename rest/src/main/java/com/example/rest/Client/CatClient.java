package com.example.rest.Client;

import com.example.buisness.DTO.CatDTO;
import com.example.buisness.Response.CarController.DeleteResponse;
import com.example.buisness.Response.CreateResponse;
import com.example.buisness.Response.FindResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cat", url = "http://localhost:8081")
public interface CatClient {
  @PostMapping("/cat/create")
  CreateResponse<CatDTO> createCat(@RequestBody CatDTO catDTO);

  @DeleteMapping("/cat/deleteByName/{name}")
  DeleteResponse deleteCatByName(@PathVariable("name") String name);

  @DeleteMapping("/cat/delete/{id}")
  DeleteResponse deleteCat(@PathVariable("id") Long id);
}
