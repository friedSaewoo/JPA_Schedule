package com.example.jpa_schedule.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private final String userEmail;
    private final String password;

    public LoginRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}