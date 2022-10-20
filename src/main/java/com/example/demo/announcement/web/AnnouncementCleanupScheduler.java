package com.example.demo.announcement.web;

import com.example.demo.announcement.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AnnouncementCleanupScheduler {

    private AnnouncementService announcementService;

    //cleans up old announcements
    @Scheduled(cron = "${eventCalendar.clean-up")
    public void cleanUpOldAnnouncements(){
        announcementService.cleanUpOldAnnouncements();
    }
}
