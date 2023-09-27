package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventViewDto {

    Long id;
    String reference;
    String transactionCurrency;
    double amount;
    String accountNumber;
    String accountName;
    String accountCurrency;
    String beneficiaryName;
    String customerInfoMarkerDescription;
    String accountInformativeMarkerDescription;
    String outcome;
    String extension;
    String contactPerson;
    String customerCalledOn;
    String comment;

}
