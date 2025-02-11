package com.example.jpa_schedule.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String userEmail;
    private final String userName;
    private final String password;

    public SignUpRequestDto(String userEmail, String userName, String password) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
    }
}

