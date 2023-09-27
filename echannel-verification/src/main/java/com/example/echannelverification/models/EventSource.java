package com.example.echannelverification.models;

import com.example.echannelverification.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EventSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String business_key;
    private String priority;
    private String source_bu;
    private String dcp_reference;
    private String account_name;
    private String transaction_currency;
    private double transaction_amount;
    private String locked_by;
    private String debit_account_number;
    private String account_currency;
    private String beneficiary_name;
    private String cust_info_mkr;
    private String account_info_mkr;
    private String outcome;
    private String extension;
    private String contact_person;
    private String customer_called_on;
    private String comment;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdOn;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updatedOn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;
}
