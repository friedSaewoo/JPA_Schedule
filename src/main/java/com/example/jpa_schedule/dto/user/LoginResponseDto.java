package com.example.jpa_schedule.dto.user;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long userId;
    private final String userEmail;
    private final String userName;

    public LoginResponseDto(Long userId,String userEmail, String userName) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
    }
}
