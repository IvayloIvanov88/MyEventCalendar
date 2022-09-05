package com.example.demo.announcement.model.dto;

import com.example.demo.announcement.model.entity.AnnouncementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    AnnouncementEntity mapAnnouncementDtoToEntity(AnnouncementDTO dto);

    AnnouncementDTO mapAnnouncementEntityToDto(AnnouncementEntity entity);
}
