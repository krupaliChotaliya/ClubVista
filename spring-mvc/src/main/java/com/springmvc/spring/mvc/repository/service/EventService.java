package com.springmvc.spring.mvc.repository.service;

import com.springmvc.spring.mvc.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(int eventId);

    void updateEvent(EventDto eventDto);

    void delete(long eventId);
}
