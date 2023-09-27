package com.example.echannelverification.converters;

import com.example.echannelverification.dtos.EventResponseDto;
import com.example.echannelverification.models.EventSource;
import org.springframework.stereotype.Component;

@Component
public class EventSourceToEventResponseDto {

    public EventResponseDto converter(EventSource eventSource){
        if(eventSource.getUser() != null){
            System.out.println(eventSource.getUser().getUsername());
        }
        return EventResponseDto.builder()
                .id(eventSource.getId())
                .createdTime(eventSource.getCreatedOn())
                .priority(eventSource.getPriority())
                .sourceBU(eventSource.getSource_bu())
                .reference(eventSource.getBusiness_key())
                .dcpReference(eventSource.getDcp_reference())
                .accountName(eventSource.getAccount_name())
                .transCcy(eventSource.getTransaction_currency())
                .transAmount(eventSource.getTransaction_amount())
                .lockedBy(eventSource.getLocked_by())
                .build();
    }
}
