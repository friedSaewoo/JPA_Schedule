package com.example.jpa_schedule.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    @NotBlank(message = "현재 비밀번호를 입력해주세요")
    @Size(min=8,message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private final String oldPassword;
    @NotBlank(message = "신규 비밀번호를 입력해주세요")
    @Size(min=8,message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
