package com.example.buisness.Response.UserController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteResponse {
  private String message;
  private Long deleted_id;
}
