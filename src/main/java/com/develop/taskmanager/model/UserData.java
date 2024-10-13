package com.develop.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = Constants.USER_ENTITY)
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String username;
    public String password;
    public String mobilenumber;
}
