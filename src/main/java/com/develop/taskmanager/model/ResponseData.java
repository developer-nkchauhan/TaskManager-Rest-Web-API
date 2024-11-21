package com.develop.taskmanager.model;

import jakarta.annotation.Nullable;
import lombok.Data;
import java.util.List;

@Data
public class ResponseData {
    int statusCode;
    String message;
    List<UserData> responseBody;

    public ResponseData(int i, String s, @Nullable List<UserData> resBody) {
        this.statusCode = i;
        this.message = s;
        this.responseBody = resBody;
    }
}
