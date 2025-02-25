package com.example.anton_task1.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteResponse {
  private Long deleted_id;
  private String message;
}
