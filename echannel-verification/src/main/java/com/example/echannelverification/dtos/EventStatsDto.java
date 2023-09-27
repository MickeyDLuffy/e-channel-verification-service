package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventStatsDto {

    int notCalled;
    int callbackSuccessful;
    int callbackUnsuccessful;
}
