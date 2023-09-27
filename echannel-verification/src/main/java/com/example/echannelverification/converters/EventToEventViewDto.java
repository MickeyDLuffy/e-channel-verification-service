package com.example.echannelverification.converters;

import com.example.echannelverification.dtos.EventViewDto;
import com.example.echannelverification.models.EventSource;
import org.springframework.stereotype.Component;

@Component
public class EventToEventViewDto {

    public EventViewDto convert(EventSource source){
        return EventViewDto.builder()
                .id(source.getId())
                .reference(source.getBusiness_key())
                .transactionCurrency(source.getTransaction_currency())
                .amount(source.getTransaction_amount())
                .accountNumber(source.getDebit_account_number())
                .accountName(source.getAccount_name())
                .accountCurrency(source.getAccount_currency())
                .beneficiaryName(source.getBeneficiary_name())
                .customerInfoMarkerDescription(source.getCust_info_mkr())
                .accountInformativeMarkerDescription(source.getAccount_info_mkr())
                .outcome(source.getOutcome())
                .extension(source.getExtension())
                .contactPerson(source.getContact_person())
                .customerCalledOn(source.getCustomer_called_on())
                .comment(source.getComment())
                .build();
    }
}
