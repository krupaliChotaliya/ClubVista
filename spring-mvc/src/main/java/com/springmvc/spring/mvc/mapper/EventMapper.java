package com.springmvc.spring.mvc.mapper;

import com.springmvc.spring.mvc.dto.EventDto;
import com.springmvc.spring.mvc.models.Event;
public class EventMapper {
    public static Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photourl(eventDto.getPhotourl())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .club(eventDto.getClub())
                .build();
    }
    public static EventDto mapToEventDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photourl(event.getPhotourl())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
    }
}
