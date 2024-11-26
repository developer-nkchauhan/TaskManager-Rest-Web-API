package com.develop.taskmanager.services;

import com.develop.taskmanager.model.ResponseData;
import com.develop.taskmanager.model.UserData;
import com.develop.taskmanager.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired private UserEntityRepository repository;

    public UserService() {
    }

    public ResponseEntity<ResponseData> getUsersByName(String name){
        List<UserData> userList = new ArrayList<>();
        if(Objects.equals(name, "*")){
            userList = repository.findAll();
        }else{
            UserData user = repository.findByUsername(name);
            if(user != null){
                userList.add(user);
            }
        }

        if(!userList.isEmpty()){
            return ResponseEntity.ok(new ResponseData(200,userList.size() > 1 ? "Users found successfully" : "User found successfully",userList));
        }else{
            return ResponseEntity.ok(new ResponseData(404,"No User found",null));
        }
    }
}
