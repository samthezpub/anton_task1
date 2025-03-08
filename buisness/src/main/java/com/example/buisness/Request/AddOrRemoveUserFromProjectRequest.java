package com.example.buisness.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOrRemoveUserFromProjectRequest {
  private Long userId;
  private Long projectId;
}
