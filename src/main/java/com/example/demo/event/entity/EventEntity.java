package com.example.demo.event.entity;

import com.example.demo.announcement.model.entity.BaseEntity;
import com.example.demo.event.enums.EventType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Table(name = "events")
@Entity
public class EventEntity extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @NotNull
    @Column(name = "occurrence")
    private Instant occurrence;
}
