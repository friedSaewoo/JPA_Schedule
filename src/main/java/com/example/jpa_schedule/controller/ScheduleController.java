package com.example.jpa_schedule.controller;

import com.example.jpa_schedule.dto.schedule.ScheduleTodoModifyDto;
import com.example.jpa_schedule.dto.user.LoginResponseDto;
import com.example.jpa_schedule.dto.schedule.ScheduleCreateRequestDto;
import com.example.jpa_schedule.dto.schedule.ScheduleResponseDto;
import com.example.jpa_schedule.service.ScheduleService;
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
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> registSchedule(
            @RequestBody @Valid ScheduleCreateRequestDto requestDto,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        return new ResponseEntity<>(
                scheduleService.registSchedule(userId, requestDto.getTitle(), requestDto.getTodo())
                , HttpStatus.OK
        );
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {

        ScheduleResponseDto scheduleresponseDto = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(scheduleresponseDto, HttpStatus.OK);
    }

    @PatchMapping("/modifyTodo/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> modifyTodo(@PathVariable Long scheduleId,
                                                          @Valid @RequestBody ScheduleTodoModifyDto modifyDto,
                                                          HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();
        log.info("scheduleId = {}, userId ={}, todo = {}",scheduleId,userId,modifyDto.getTodo());

         ScheduleResponseDto responseDto=scheduleService.modifyTodo(scheduleId,userId,modifyDto.getTodo());

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId,
                                               HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        scheduleService.deleteSchedule(scheduleId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
