package com.example.demo.home.users.service;


import com.example.demo.home.users.model.entity.UserEntity;

public interface UserService {
    UserEntity getOrCreateUser(String email);
}
