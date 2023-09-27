package com.example.echannelverification.converters;

import com.example.echannelverification.dtos.UserResponseDto;
import com.example.echannelverification.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDTO {

    public UserResponseDto converter(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
