package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class EventUpdateRequestDto {

    String outcome;
    String extension;
    String contactPerson;
    String customerCalledOn;
    String comment;
    String status;
    Long userId;

}
