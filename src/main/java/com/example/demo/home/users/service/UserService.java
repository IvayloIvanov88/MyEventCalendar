package com.example.demo.home.users.service;


import com.example.demo.home.users.model.entity.UserEntity;

public interface UserService {
    void createAndLoginUser(String email, String password);

    boolean existsUser(String email);

    UserEntity getOrCreateUser(String email);
}
