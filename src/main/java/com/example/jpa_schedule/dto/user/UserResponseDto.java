package com.example.jpa_schedule.dto.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String userEmail;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UserResponseDto(Long userId, String userEmail, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
