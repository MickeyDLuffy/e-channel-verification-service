package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiExceptionResponse {
    private String message;
    private HttpStatus status;
}