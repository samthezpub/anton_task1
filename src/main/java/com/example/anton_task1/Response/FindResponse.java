package com.example.anton_task1.Response;

import com.example.anton_task1.Entity.UserEntity;

public class FindResponse {
    private Long id;
    private String message;
    private UserEntity user;

    public FindResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
