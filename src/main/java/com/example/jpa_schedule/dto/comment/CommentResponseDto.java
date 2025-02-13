package com.example.jpa_schedule.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final String comment;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Long commentId, String comment, Long userId, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
