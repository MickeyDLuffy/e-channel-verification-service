package com.example.echannelverification.controllers;

import com.example.echannelverification.dtos.LoginRequestDto;
import com.example.echannelverification.dtos.UserResponseDto;
import com.example.echannelverification.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
