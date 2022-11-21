package com.example.demo.announcement;

import com.example.demo.announcement.model.dto.AnnouncementDTO;
import com.example.demo.announcement.model.entity.AnnouncementEntity;
import com.example.demo.announcement.repository.AnnouncementRepository;
import com.example.demo.announcement.service.impl.AnnouncementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnouncementServiceTest {

    private AnnouncementServiceImpl serviceToTest;

    @Mock
    AnnouncementRepository mockAnnouncementRepository;


    @BeforeEach
    public void setUp() {
        serviceToTest = new AnnouncementServiceImpl(mockAnnouncementRepository, new ModelMapper());
    }

    @Test
    public void testFindAll() {
        // Arrange
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setTitle("TestAnnouncement");
        announcementEntity.setDescription("TestDescription");
        announcementEntity.setUpdatedOn(Instant.now());
        announcementEntity.setCreatedOn(Instant.now());

        when(mockAnnouncementRepository.findAll()).thenReturn(List.of(announcementEntity));

        //act
        List<AnnouncementDTO> announcementDTOs = serviceToTest.findAll();

        //assert
        Assertions.assertEquals(1, announcementDTOs.size());
        AnnouncementDTO actualDTO = announcementDTOs.get(0);

        Assertions.assertEquals(announcementEntity.getTitle(), actualDTO.getTitle());
        Assertions.assertEquals(announcementEntity.getDescription(), actualDTO.getDescription());
        Assertions.assertEquals(announcementEntity.getCreatedOn(), actualDTO.getCreatedOn());
    }
}

