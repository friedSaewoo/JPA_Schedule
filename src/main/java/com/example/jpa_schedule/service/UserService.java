package com.example.jpa_schedule.service;

import com.example.jpa_schedule.dto.SignUpResponseDto;

public interface UserService {

    SignUpResponseDto signUp(String userEmail, String password, String userName);
}
