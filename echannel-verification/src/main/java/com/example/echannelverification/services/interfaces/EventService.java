package com.example.echannelverification.services.interfaces;

import com.example.echannelverification.dtos.*;

import java.util.List;

public interface EventService {

    List<EventResponseDto> getEventsWithUnassignedStatus();

    List<EventResponseDto> assignEventsToUser(Long id, EventsToAssignDto eventsToAssignDto);

    EventResponseDto createEvent(EventRequestDto eventRequestDto);

    EventViewDto getEvent(Long id);

    EventViewDto updateEvent(EventUpdateRequestDto eventUpdateRequestDto, Long eventId);

    EventStatsDto getEventsStatistics();
}
