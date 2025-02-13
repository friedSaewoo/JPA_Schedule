package com.example.jpa_schedule.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "comment is empty")
    @Size(max=100,message = "100자 이내로 입력하세요")
    private final String comment;

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }
}
