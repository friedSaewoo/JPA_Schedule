package com.example.jpa_schedule.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message="이메일을 입력해주세요")
    private final String userEmail;
    @NotBlank(message="비밀번호를 입력해주세요")
    @Size(min=8,message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private final String password;

    public LoginRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}