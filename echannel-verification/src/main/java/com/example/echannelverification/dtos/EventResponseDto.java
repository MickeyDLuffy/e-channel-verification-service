package com.example.echannelverification.dtos;

import com.example.echannelverification.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
public class EventResponseDto {
    Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date createdTime;
    String priority;
    String sourceBU;
    String reference;
    String dcpReference;
    String accountName;
    String transCcy;
    double transAmount;
    String lockedBy;
}
