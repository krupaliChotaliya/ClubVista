package com.springmvc.spring.mvc.repository.service.impl;

import com.springmvc.spring.mvc.dto.EventDto;
import com.springmvc.spring.mvc.models.Event;
import com.springmvc.spring.mvc.models.club;
import com.springmvc.spring.mvc.repository.ClubRepository;
import com.springmvc.spring.mvc.repository.EventRepository;
import com.springmvc.spring.mvc.repository.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.springmvc.spring.mvc.mapper.EventMapper.mapToEvent;
import static com.springmvc.spring.mvc.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,ClubRepository clubRepository){
        this.eventRepository=eventRepository;
        this.clubRepository=clubRepository;
    }
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        club club=clubRepository.findById(clubId).get();
        Event event=mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {

        List<Event> events=eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(int eventId) {
       Event event= eventRepository.findById((long) eventId).get();
       return  mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        eventRepository.save(mapToEvent(eventDto));
    }

    @Override
    public void delete(long eventId) {
        eventRepository.deleteById(eventId);
    }


}
