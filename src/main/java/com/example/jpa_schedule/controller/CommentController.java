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

    /**
     * 댓글 작성
     * @param scheduleId 클라이언트 측에서 보내는 일정 ID
     * @param commentRequestDto 작성할 댓글 정보가 담긴 객체
     * @param request session 정보가 담긴 객체
     * @return 생성된 댓글 정보, HTTP 상태코드 200(OK)
     */
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

    /**
     * 댓글 단건 조회
     * @param commentId 조회할 댓글 ID
     * @return 조회한 댓글 정보, HTTP 상태코드 200(OK)
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long commentId){

        return new ResponseEntity<>(commentService.findById(commentId),HttpStatus.OK);
    }

    /**
     * 댓글 내용수정
     * @param commentId 수정할 댓글 ID
     * @param requestDto 수정할 정보가 담긴 객체
     * @param request session의 user정보를 확인 (본인 댓글만 수정가능)
     * @return 수정된 댓글 정보, HTTP 상태코드 200(OK)
     */
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

    /**
     * 댓글 삭제
     * @param commentId 삭제할 댓글 ID
     * @param request session의 user정보를 확인 (본인 댓글만 삭제가능)
     * @return HTTP 상태코드 200(OK)
     */
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
