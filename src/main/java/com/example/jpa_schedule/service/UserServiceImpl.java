package com.example.jpa_schedule.service;

import com.example.jpa_schedule.UserRepository.UserRepository;
import com.example.jpa_schedule.dto.SignUpResponseDto;
import com.example.jpa_schedule.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public SignUpResponseDto signUp(String userEmail, String password, String userName) {
        User user = new User(userEmail, password, userName);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getCreatedAt()
        );
    }
}
