package com.example.jpa_schedule.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요")
    private final String userEmail;

    @NotBlank(message = "이름을 입력해주세요.")
    private final String userName;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private final String password;

    public SignUpRequestDto(String userEmail, String userName, String password) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
    }
}

