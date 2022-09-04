package com.example.demo.announcement.model;

import com.example.demo.announcement.model.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Entity
@Table(name = "announcements")
public class AnnouncementEntity extends BaseEntity {

    @NotNull
    @Column(name = "created_on")
    private Instant createdOn;

    @NotNull
    @Column(name = "updated_on")
    private Instant updated_on;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;
}
