package com.develop.taskmanager.model;

import lombok.Data;

@Data
public class ResponseData {
    int statusCode;
    String message;

    public ResponseData(int i, String s) {
        this.statusCode = i;
        this.message = s;
    }
}
