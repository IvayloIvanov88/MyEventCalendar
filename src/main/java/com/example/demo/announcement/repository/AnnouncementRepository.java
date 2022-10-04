package com.example.demo.announcement.repository;

import com.example.demo.announcement.model.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity,Long> {

    void deleteByUpdatedOnBefore(Instant endTime);
}
