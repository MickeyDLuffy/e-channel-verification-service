package com.example.echannelverification.controllers;

import com.example.echannelverification.dtos.*;
import com.example.echannelverification.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventResponseDto>> getEventsWithUnassignedStatus() {
        return new ResponseEntity<>(eventService.getEventsWithUnassignedStatus(), HttpStatus.OK);
    }

    @PutMapping("/assign")
    public ResponseEntity<List<EventResponseDto>> assignEventToUser(@RequestParam Long id, @RequestBody EventsToAssignDto eventsToAssignDto) {
        return new ResponseEntity<>(eventService.assignEventsToUser(id, eventsToAssignDto), HttpStatus.OK);
    }

    //to delete later
    @PostMapping()
    public ResponseEntity<EventResponseDto> createEventSource(@RequestBody EventRequestDto eventRequestDto) {
        return new ResponseEntity<>(eventService.createEvent(eventRequestDto), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<EventViewDto> getEvent(@RequestParam Long id) {
        return new ResponseEntity<>(eventService.getEvent(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EventViewDto> updateEvent(@RequestBody EventUpdateRequestDto eventUpdateRequestDto, @RequestParam Long eventId) {
        return new ResponseEntity<>(eventService.updateEvent(eventUpdateRequestDto, eventId), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<EventStatsDto> getEventsStatistics(){
        return new ResponseEntity<>(eventService.getEventsStatistics(), HttpStatus.OK);
    }
}
