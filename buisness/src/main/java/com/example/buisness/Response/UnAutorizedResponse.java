package com.example.buisness.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnAutorizedResponse {
  private int code;
  private String message;
}
