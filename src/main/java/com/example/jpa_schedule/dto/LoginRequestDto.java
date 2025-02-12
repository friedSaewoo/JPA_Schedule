package com.example.jpa_schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Email
    @NotBlank
    private final String userEmail;
    @NotBlank
    private final String password;

    public LoginRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}