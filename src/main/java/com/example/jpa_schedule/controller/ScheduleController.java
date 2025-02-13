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
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    /**
     * 일정 등록
     * @param requestDto 등록할 일정 정보가 담긴 객체
     * @param request Session의 user정보 확인
     * @return 생성된 일정 정보, HTTP 상태코드 200(OK)
     */
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

    /**
     * 일정 단건조회
     * @param scheduleId 조회할 일정 ID
     * @return 조회된 일정 정보, HTTP 상태코드 200(OK)
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {

        ScheduleResponseDto scheduleresponseDto = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(scheduleresponseDto, HttpStatus.OK);
    }

    /**
     * 일정 TODO 수정
     * @param scheduleId 수정할 일정 ID
     * @param modifyDto 수정할 일정의 정보가 담긴 객체
     * @param request session의 user정보 확인(본인 일정만 수정가능)
     * @return 수정된 일정 정보, HTTP 상태코드 200(OK)
     */
    @PatchMapping("/modifyTodo/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> modifyTodo(@PathVariable Long scheduleId,
                                                          @Valid @RequestBody ScheduleTodoModifyDto modifyDto,
                                                          HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        ScheduleResponseDto responseDto = scheduleService.modifyTodo(scheduleId, userId, modifyDto.getTodo());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @param scheduleId 삭제할 일정 ID
     * @param request session의 user정보 확인(본인 일정만 삭제 가능)
     * @return HTTP상태코드 200(OK)
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId,
                                               HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        LoginResponseDto user = (LoginResponseDto) session.getAttribute("user");
        Long userId = user.getUserId();

        scheduleService.deleteSchedule(scheduleId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 일정 페이지네이션
     * @param page 페이지 번호 default = 0
     * @param size 페이지 크기 default = 10
     * @return 일정 정보가 담긴 Page객체 반환, HTTP 상태코드 200 (OK)
     */
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ScheduleResponseDto>>> findScheduleList(@RequestParam(defaultValue = "0") int page,
                                                                                         @RequestParam(defaultValue = "10") int size,
                                                                                         PagedResourcesAssembler<ScheduleResponseDto> assembler) {

        PagedModel<EntityModel<ScheduleResponseDto>> scheduleList = scheduleService.findScheduleList(page, size, assembler);
        return ResponseEntity.ok(scheduleList);
    }

}
