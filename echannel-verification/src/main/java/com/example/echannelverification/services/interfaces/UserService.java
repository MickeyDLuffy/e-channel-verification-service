package com.example.echannelverification.services.interfaces;

import com.example.echannelverification.dtos.LoginRequestDto;
import com.example.echannelverification.dtos.UserResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface UserService {

    UserResponseDto login(LoginRequestDto loginRequestDto);

    List<UserResponseDto> getUsers();
}
