package com.example.echannelverification.repositories;

import com.example.echannelverification.dtos.UserResponseDto;
import com.example.echannelverification.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
