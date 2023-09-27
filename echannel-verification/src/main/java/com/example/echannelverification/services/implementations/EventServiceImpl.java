package com.example.echannelverification.services.implementations;

import com.example.echannelverification.converters.EventSourceToEventResponseDto;
import com.example.echannelverification.converters.EventToEventViewDto;
import com.example.echannelverification.dtos.*;
import com.example.echannelverification.enums.Status;
import com.example.echannelverification.exceptions.ResourceNotFoundException;
import com.example.echannelverification.models.EventSource;
import com.example.echannelverification.models.User;
import com.example.echannelverification.repositories.EventRepository;
import com.example.echannelverification.repositories.UserRepository;
import com.example.echannelverification.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventToEventViewDto eventToEventViewDto;
    private final EventSourceToEventResponseDto eventSourceToEventResponseDto;


    @Override
    public List<EventResponseDto> getEventsWithUnassignedStatus() {
        List<EventSource> unassignedEvents = eventRepository.findAllByStatus(Status.UNASSIGNED);
        return unassignedEvents.stream()
                .map(eventSourceToEventResponseDto::converter)
                .toList();
    }

    @Override
    public List<EventResponseDto> assignEventsToUser(Long id, EventsToAssignDto eventsToAssignDto) {
        User user = getUserFromDb(id);
        List<EventSource> eventSources = eventsToAssignDto.getEventSourceIds().stream()
                .map(this::getEventSourceFromDb)
                .toList();
        eventSources.forEach(eventSource -> {
                    eventSource.setUser(user);
                    eventSource.setStatus(Status.PROCEED);
                }
        );
        eventRepository.saveAll(eventSources);
        return eventSources.stream()
                .map(eventSourceToEventResponseDto::converter)
                .toList();
    }

    @Override
    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        EventSource eventSource = new EventSource();
        eventSource.setAccount_name(eventRequestDto.getAccount_name());
        eventSource.setBusiness_key(eventRequestDto.getBusiness_key());
        eventSource.setDcp_reference(eventRequestDto.getDcp_reference());
        eventSource.setTransaction_currency(eventRequestDto.getTransaction_currency());
        eventSource.setPriority(eventRequestDto.getPriority());
        eventSource.setTransaction_amount(eventRequestDto.getTransaction_amount());
        eventSource.setSource_bu(eventRequestDto.getSource_bu());
        eventSource.setStatus(Status.UNASSIGNED);
        eventRepository.save(eventSource);
        return eventSourceToEventResponseDto.converter(eventSource);
    }

    @Override
    public EventViewDto getEvent(Long id) {
        return eventToEventViewDto.convert(getEventSourceFromDb(id));
    }

    @Override
    public EventViewDto updateEvent(EventUpdateRequestDto eventUpdateRequestDto, Long eventId) {
        User user = getUserFromDb(eventUpdateRequestDto.getUserId());
        EventSource eventSource = getEventSourceFromDb(eventId);
        eventSource.setOutcome(eventUpdateRequestDto.getOutcome());
        eventSource.setExtension(eventUpdateRequestDto.getExtension());
        eventSource.setContact_person(eventUpdateRequestDto.getContactPerson());
        eventSource.setCustomer_called_on(eventUpdateRequestDto.getCustomerCalledOn());
        eventSource.setComment(eventUpdateRequestDto.getComment());
        eventSource.setStatus(EnumUtils.findEnumInsensitiveCase(Status.class, eventUpdateRequestDto.getStatus().toUpperCase()));
//        eventSource.setUpdatedBy(); TODO: set the last updated by
        eventRepository.save(eventSource);
        return eventToEventViewDto.convert(eventSource);
    }

    @Override
    public EventStatsDto getEventsStatistics() {
        List<EventSource> events = eventRepository.findAll();

        long unSuccessfulCallback = events.stream()
                .filter(event -> event.getStatus() == Status.REJECT)
                .count();

        long successfulCallback = events.stream()
                .filter(event -> event.getStatus() == Status.PROCEED)
                .count();

        long unCalled = events.stream()
                .filter(event -> event.getStatus() == Status.UNASSIGNED)
                .count();

        return EventStatsDto.builder()
                .callbackUnsuccessful((int) unSuccessfulCallback)
                .callbackSuccessful((int) successfulCallback)
                .notCalled((int) unCalled)
                .build();
    }

    private EventSource getEventSourceFromDb(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event source does not exist"));
    }

    private User getUserFromDb(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
