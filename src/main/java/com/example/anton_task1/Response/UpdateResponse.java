package com.example.anton_task1.Response;

import com.example.anton_task1.Entity.UserEntity;

public class UpdateResponse {
    private Long id;
    private String message;
    private UserEntity user;

    public UpdateResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
