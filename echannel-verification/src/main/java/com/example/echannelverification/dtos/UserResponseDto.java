package com.example.echannelverification.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    Long id;
    String username;
}
