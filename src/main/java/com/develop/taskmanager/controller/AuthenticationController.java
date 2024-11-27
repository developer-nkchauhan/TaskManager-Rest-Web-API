package com.develop.taskmanager.controller;

import com.develop.taskmanager.model.*;
import com.develop.taskmanager.services.SignUpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Validated
@Tag(name = "Authentication APIs")
public class AuthenticationController {

    @Autowired
    private SignUpService signUpService;

    public AuthenticationController() {

    }

    @PostMapping(Constants.ROUTE_SIGN_UP)
    public ResponseEntity<ResponseData> doSignUp(@RequestBody UserRequestBody userReq){
        return signUpService.signUpUser(userReq);
    }

    @PostMapping(Constants.ROUTE_LOGIN)
    public ResponseEntity<LoginResponseData> doLogin(@RequestParam String username,@RequestParam String password) {
        UserData uData = new UserData();
        uData.username = username;
        uData.password = password;
        uData.mobilenumber = "";
        return signUpService.doLogin(uData);
    }
}
