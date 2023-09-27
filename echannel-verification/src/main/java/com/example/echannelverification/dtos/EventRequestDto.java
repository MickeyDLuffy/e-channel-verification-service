package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class EventRequestDto {
    String business_key;
    String priority;
    String source_bu;
    String dcp_reference;
    String account_name;
    String transaction_currency;
    double transaction_amount;
    String locked_by;
    String debit_account_number;
    String account_currency;
    String beneficiary_name;
    String cust_info_mkr;
    String account_info_mkr;
    String outcome;
    String extension;
    String contact_person;
    String customer_called_on;
    String comment;

}
