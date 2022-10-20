package com.example.demo.announcement.service;

import com.example.demo.announcement.model.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementService {
    void cleanUpOldAnnouncements();

    void createOrUpdateAnnouncement(AnnouncementDTO announcementDTO);

    void deleteAnnouncement(long announcementId);

    List<AnnouncementDTO> findAll();
}
