package com.example.anton_task1.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserFromCourseRequest {
    private Long userId;
    private Long courseId;
}
