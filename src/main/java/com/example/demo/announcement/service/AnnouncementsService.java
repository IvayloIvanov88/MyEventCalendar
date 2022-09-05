package com.example.demo.announcement.service;

import com.example.demo.announcement.model.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementsService {
    List<AnnouncementDTO> findAll();
}
