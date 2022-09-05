package com.example.demo.init;

import com.example.demo.announcement.model.entity.AnnouncementEntity;
import com.example.demo.announcement.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@Component
public class EventCalendarInit implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;


    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count() == 0) {
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle("Hello to Spring Boot");
            announcementEntity.setDescription("Spring advanced course!");
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
            announcementRepository.saveAndFlush(announcementEntity);
        }
    }
}
