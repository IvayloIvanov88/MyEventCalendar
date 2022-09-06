package com.example.demo.users.model;

import com.example.demo.announcement.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(name = "role", nullable = false)
    private String role;
}
