package com.example.jpa_schedule.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank
    private final String comment;

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }
}
