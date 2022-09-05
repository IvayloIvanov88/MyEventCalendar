package com.example.demo.announcement.service.impl;

import com.example.demo.announcement.model.dto.AnnouncementDTO;
import com.example.demo.announcement.model.dto.AnnouncementMapper;
import com.example.demo.announcement.repository.AnnouncementRepository;
import com.example.demo.announcement.service.AnnouncementsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementRepository announcementRepository;
    private final ModelMapper modelMapper;

// mapstruct
//    @Override
//    public List<AnnouncementDTO> findAll() {
//        return announcementRepository
//                .findAll()
//                .stream()
//                .map(AnnouncementMapper.INSTANCE::mapAnnouncementEntityToDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<AnnouncementDTO> findAll() {
        return announcementRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, AnnouncementDTO.class))
                .collect(Collectors.toList());

    }
}
