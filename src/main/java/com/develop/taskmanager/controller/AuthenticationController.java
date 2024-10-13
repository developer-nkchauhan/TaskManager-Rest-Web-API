package com.develop.taskmanager.controller;

import com.develop.taskmanager.services.SignUpService;
import com.develop.taskmanager.model.LoginResponseData;
import com.develop.taskmanager.model.ResponseData;
import com.develop.taskmanager.model.UserRequestBody;
import com.develop.taskmanager.model.UserData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
@Validated
@Tag(name = "Authentication APIs")
public class AuthenticationController {

    @Autowired
    private SignUpService signUpService;

    public AuthenticationController() {

    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseData> doSignUp(@RequestBody UserRequestBody userreq){
        return signUpService.signUpUser(userreq);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseData> doLogin(@RequestParam String username,@RequestParam String password) {
        UserData uData = new UserData();
        uData.username = username;
        uData.password = password;
        uData.mobilenumber = "";
        return signUpService.doLogin(uData);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserData>> getAllUsers()
    {
        return signUpService.getAllUsers();
    }
}
