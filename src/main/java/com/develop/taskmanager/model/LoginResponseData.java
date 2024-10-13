package com.develop.taskmanager.model;

import lombok.Data;

@Data
public class LoginResponseData {
    int statusCode;
    String message;
    String token;

    public LoginResponseData(int statusCode, String message, String token) {
        this.statusCode = statusCode;
        this.message = message;
        this.token = token;
    }
}
