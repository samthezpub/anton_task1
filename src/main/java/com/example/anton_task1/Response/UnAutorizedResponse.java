package com.example.anton_task1.Response;

public class UnAutorizedResponse {
    private String message;
    private int code;

    public UnAutorizedResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
