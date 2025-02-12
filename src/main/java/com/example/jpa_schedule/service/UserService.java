package com.example.jpa_schedule.service;

import com.example.jpa_schedule.dto.user.*;

public interface UserService {

    SignUpResponseDto signUp(String userEmail, String password, String userName);

    UserResponseDto findById(Long userId);

    void updatePassword(Long userId, UpdatePasswordRequestDto requestDto);

    void delete(Long userId);

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
