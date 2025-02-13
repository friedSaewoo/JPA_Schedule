package com.example.jpa_schedule.controller;

import com.example.jpa_schedule.dto.comment.CommentRequestDto;
import com.example.jpa_schedule.dto.comment.CommentResponseDto;
import com.example.jpa_schedule.dto.user.LoginResponseDto;
import com.example.jpa_schedule.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/writeComment/{scheduleId}")
    public ResponseEntity<CommentResponseDto> writeComment(@PathVariable Long scheduleId,
                                                           @Valid @RequestBody CommentRequestDto commentRequestDto,
                                                           HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();
        log.info("comment ={}",commentRequestDto.getComment());
        CommentResponseDto commentResponseDto=commentService.writeComment(scheduleId,userId,commentRequestDto);

        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long commentId){

        return new ResponseEntity<>(commentService.findById(commentId),HttpStatus.OK);
    }

    @PatchMapping("/modifyComment/{commentId}")
    public ResponseEntity<CommentResponseDto> moifyComment(@PathVariable Long commentId,
                                                           @Valid @RequestBody CommentRequestDto requestDto,
                                                           HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        CommentResponseDto commentResponseDto = commentService.modifyComment(commentId, userId, requestDto.getComment());

        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        commentService.deleteComment(commentId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
