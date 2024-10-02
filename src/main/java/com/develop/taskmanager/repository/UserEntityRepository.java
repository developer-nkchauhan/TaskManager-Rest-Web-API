package com.develop.taskmanager.repository;

import com.develop.taskmanager.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserData,Long> {

}
