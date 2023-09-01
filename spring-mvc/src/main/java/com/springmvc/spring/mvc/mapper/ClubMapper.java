package com.springmvc.spring.mvc.mapper;

import com.springmvc.spring.mvc.dto.ClubDto;
import com.springmvc.spring.mvc.models.Event;
import com.springmvc.spring.mvc.models.club;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.springmvc.spring.mvc.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static club mapToClub(ClubDto clubDto) {
        club club2=club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photourl(clubDto.getPhotourl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .createdBy(clubDto.getCreatedBy())
                .build();
        return club2;
    }

    public static ClubDto mapToClubDto(club club){
        ClubDto clubDto=ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photourl(club.getPhotourl())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }
}
