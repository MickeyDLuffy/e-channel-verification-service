package com.example.echannelverification.services.implementations;

import com.example.echannelverification.converters.UserToUserResponseDTO;
import com.example.echannelverification.dtos.LoginRequestDto;
import com.example.echannelverification.dtos.UserResponseDto;
import com.example.echannelverification.models.User;
import com.example.echannelverification.repositories.UserRepository;
import com.example.echannelverification.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserResponseDTO userToUserResponseDTO;

    @Override
    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findUserByUsername(loginRequestDto.getUsername());
        if (Objects.nonNull(user)) {
            return userToUserResponseDTO.converter(user);
        }
        user = new User();
        user.setUsername(loginRequestDto.getUsername());
        user.setName(loginRequestDto.getName());
        user.setPassword(loginRequestDto.getPassword());
        userRepository.save(user);
        return userToUserResponseDTO.converter(user);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userToUserResponseDTO::converter).toList();
    }
}
