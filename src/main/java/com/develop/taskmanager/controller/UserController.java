package com.develop.taskmanager.controller;

import com.develop.taskmanager.model.ResponseData;
import com.develop.taskmanager.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users")
@Validated
@Tag(name = "User Management APIs")
public class UserController {
    
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/get-users")
    public ResponseEntity<ResponseData> getUsers(@RequestParam String username) {
        return userService.getUsersByName(username);
    }
}
