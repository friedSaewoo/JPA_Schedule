package com.example.jpa_schedule.service;

import com.example.jpa_schedule.repository.CommentRepository;
import com.example.jpa_schedule.repository.ScheduleRepository;
import com.example.jpa_schedule.repository.UserRepository;
import com.example.jpa_schedule.dto.comment.CommentRequestDto;
import com.example.jpa_schedule.dto.comment.CommentResponseDto;
import com.example.jpa_schedule.entity.Comment;
import com.example.jpa_schedule.entity.Schedule;
import com.example.jpa_schedule.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public CommentResponseDto writeComment(Long scheduleId, Long userId, CommentRequestDto commentRequestDto) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Comment comment = new Comment(commentRequestDto.getComment(), findUser, findSchedule);
        commentRepository.save(comment);
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getComment(),
                comment.getUser().getUserId(),
                comment.getSchedule().getScheduleId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Override
    public CommentResponseDto findById(Long commentId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getComment(),
                comment.getUser().getUserId(),
                comment.getSchedule().getScheduleId(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    @Override
    @Transactional
    public CommentResponseDto modifyComment(Long commentId, Long userId, String comment) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        if(!findComment.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"접근권한이 없습니다.");
        }
        findComment.updateComment(comment);
        return new CommentResponseDto(
                findComment.getCommentId(),
                findComment.getComment(),
                findComment.getUser().getUserId(),
                findComment.getSchedule().getScheduleId(),
                findComment.getCreatedAt(),
                findComment.getModifiedAt()
        );
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);
        if(!findComment.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"접근권한이 없습니다.");
        }
        commentRepository.delete(findComment);
    }
}
