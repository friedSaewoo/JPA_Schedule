package com.example.jpa_schedule.service;

import com.example.jpa_schedule.dto.comment.CommentRequestDto;
import com.example.jpa_schedule.dto.comment.CommentResponseDto;
import jakarta.validation.constraints.NotBlank;

public interface CommentService {
    CommentResponseDto writeComment(Long scheduleId, Long userId, CommentRequestDto commentRequestDto);

    CommentResponseDto findById(Long commentId);

    CommentResponseDto modifyComment(Long commentId, Long userId, @NotBlank String comment);

    void deleteComment(Long commentId, Long userId);
}
