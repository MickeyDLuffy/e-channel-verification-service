package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class EventsToAssignDto {
    List<Long> eventSourceIds;
}
