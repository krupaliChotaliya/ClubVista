package com.springmvc.spring.mvc.controller;

import com.springmvc.spring.mvc.dto.ClubDto;
import com.springmvc.spring.mvc.dto.EventDto;
import com.springmvc.spring.mvc.models.Event;
import com.springmvc.spring.mvc.models.User;
import com.springmvc.spring.mvc.repository.service.EventService;
import com.springmvc.spring.mvc.repository.service.UserService;
import com.springmvc.spring.mvc.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService,UserService userService) {

        this.userService=userService;
        this.eventService = eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") int clubId, Model model) {
        model.addAttribute("clubId", clubId);
        model.addAttribute("events", new EventDto());
        return "event-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId")long clubId,
                              @Valid @ModelAttribute("events")EventDto eventDto,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "event-create";
        }
        eventService.createEvent(clubId,eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String eventList(Model model){
        User user=new User();
        List<EventDto> eventDtoList=eventService.findAllEvents();
        String username=SecurityUtil.getSessionUser();
        if(username!=null){
            user= userService.findByUsername(username);
        }
        model.addAttribute("user",user);
        model.addAttribute("events",eventDtoList);
        return "event-list";

    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId")int eventId,Model model){
        User user=new User();
        EventDto event=eventService.findEventById(eventId);
        String username=SecurityUtil.getSessionUser();
        if(username!=null){
            user= userService.findByUsername(username);
        }
        model.addAttribute("user",user);
        model.addAttribute("event",event);
        return "event-details";
    }

    @GetMapping("/events/{eventId}/edit")
    public String updateEventForm(@PathVariable("eventId") int eventId, Model model) {
        EventDto eventDto=eventService.findEventById(eventId);
        model.addAttribute("event",eventDto);
        return "event-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId")int eventId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "event-edit";
        }
        EventDto eventDto1=eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(eventDto1.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") int eventId,Model model){
        eventService.delete(eventId);
        return "redirect:/events";
    }
}
