package com.example.anton_task1.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOrRemoveUserFromProjectRequest {
    private Long projectId;
    private Long userId;
}
