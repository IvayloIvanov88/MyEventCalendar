package com.example.demo.announcement.service.impl;

import com.example.demo.announcement.model.dto.AnnouncementDTO;
import com.example.demo.announcement.model.entity.AnnouncementEntity;
import com.example.demo.announcement.repository.AnnouncementRepository;
import com.example.demo.announcement.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

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

    @Override
    public void cleanUpOldAnnouncements() {
        Instant endTime = Instant.now().minus(7, ChronoUnit.DAYS);
        announcementRepository.deleteByUpdatedOnBefore(endTime);
    }

    @Override
    public void createOrUpdateAnnouncement(AnnouncementDTO announcementDTO){
        AnnouncementEntity announcementEntity = modelMapper.map(announcementDTO, AnnouncementEntity.class);

        if(announcementEntity.getCreatedOn()==null){
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
        }

        announcementRepository.save(announcementEntity);
    }

    @Override
    public void deleteAnnouncement(long announcementId) {
        announcementRepository.deleteById(announcementId);
    }
}
