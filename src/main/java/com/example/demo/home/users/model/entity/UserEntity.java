package com.example.demo.home.users.model.entity;

import com.example.demo.announcement.model.entity.BaseEntity;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ToString
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    private boolean isActive;

    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public UserEntity setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
