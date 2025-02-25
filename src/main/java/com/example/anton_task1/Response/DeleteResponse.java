package com.example.anton_task1.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

public class DeleteResponse {
    private Long deleted_id;
    private String message;

    public DeleteResponse() {
    }

    public Long getDeleted_id() {
        return deleted_id;
    }

    public void setDeleted_id(Long deleted_id) {
        this.deleted_id = deleted_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
