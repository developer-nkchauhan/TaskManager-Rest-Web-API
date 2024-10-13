package com.develop.taskmanager.services;

import com.develop.taskmanager.authenticator.JwtUtil;
import com.develop.taskmanager.model.LoginResponseData;
import com.develop.taskmanager.model.ResponseData;
import com.develop.taskmanager.model.UserData;
import com.develop.taskmanager.model.UserRequestBody;
import com.develop.taskmanager.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {

    @Autowired private UserEntityRepository userRepository;
    @Autowired private JwtUtil jwtUtil;


    public SignUpService() {
    }

    public ResponseEntity<ResponseData> signUpUser(UserRequestBody userreq)  {
        UserData uData = new UserData();
        uData.username = userreq.username;
        uData.mobilenumber = userreq.mobilenumber;
        uData.password = userreq.password;

        String userExists = checkUserExistAlready(uData);
        if(!userExists.isEmpty()){
            return ResponseEntity.ok(new ResponseData(401,userExists));
        }
        UserData optRegister = userRepository.save(uData);
        if(!optRegister.username.isEmpty()){
            return ResponseEntity.ok(new ResponseData(200,"User signed-up successfully"));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private String checkUserExistAlready(UserData uData) {
        UserData existsByUsername = userRepository.findByUsername(uData.username);
        if(existsByUsername != null && !existsByUsername.username.isEmpty()){
            return "Entered username already Exists";
        }
        UserData existByMobilenumber = userRepository.findByMobilenumber(uData.mobilenumber);
        if(existByMobilenumber != null && !existByMobilenumber.mobilenumber.isEmpty()){
            return "Entered mobile number already Exists..";
        }
        return "";
    }

    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> userList = userRepository.findAll();
        if(!userList.isEmpty()){
            return ResponseEntity.ok(userList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<LoginResponseData> doLogin(UserData user) {
        String userExists = checkUserExistAlready(user);
        if(userExists.isEmpty()){
            return ResponseEntity.ok(new LoginResponseData(401,"Invalid Credentials..",""));
        }
        String token = generateToken(user);
        return ResponseEntity.ok(new LoginResponseData(200,"Logged in Successful",token));
    }

    private String generateToken(UserData user) {
        return jwtUtil.createToken(user.username+user.password);
    }
}
