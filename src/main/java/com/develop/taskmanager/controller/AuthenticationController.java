package com.develop.taskmanager.controller;

import com.develop.taskmanager.model.UserRequestBody;
import com.develop.taskmanager.repository.UserEntityRepository;
import com.develop.taskmanager.model.UserData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@Validated
@Tag(name = "Authentication APIs")
public class AuthenticationController {

    private final UserEntityRepository userRepo;

    public AuthenticationController(UserEntityRepository userEntityRepository) {
        this.userRepo = userEntityRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserData> doSignUp(@RequestBody UserRequestBody userreq){
        UserData uData = new UserData();
        uData.username = userreq.username;
        uData.mobilenumber = userreq.mobilenumber;
        uData.password = userreq.password;
        Optional<UserData> optRegister = Optional.of(userRepo.save(uData));
        if(optRegister.isPresent()){
            return ResponseEntity.ok(optRegister.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/login")
//    public String doLogin(@RequestBody User user) {
//        return authService.generateToken();
//    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserData>> getAllUsers()
    {
        List<UserData> users = userRepo.findAll();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
