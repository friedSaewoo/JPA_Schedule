package com.example.jpa_schedule.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpResponseDto {
    private final Long userId;

    private final String userName;

    private final String userEmail;

    private final LocalDateTime createdAt;

    public SignUpResponseDto(Long userId, String userName, String userEmail, LocalDateTime createdAt) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }
}
