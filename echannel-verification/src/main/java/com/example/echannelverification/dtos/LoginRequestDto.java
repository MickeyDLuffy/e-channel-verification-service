package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LoginRequestDto {

    String username;
    String password;
    String name;
}
