package com.example.jpa_schedule.controller;

import com.example.jpa_schedule.dto.user.*;
import com.example.jpa_schedule.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signUp(
                requestDto.getUserEmail(),
                requestDto.getPassword(),
                requestDto.getUserName()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById (@PathVariable Long userId){
        UserResponseDto userResponseDto = userService.findById(userId);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    @PatchMapping("/updatePassword/{userId}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId,
                                               @RequestBody @Valid UpdatePasswordRequestDto requestDto){

        userService.updatePassword(userId,requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId){

        userService.delete(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                                  HttpServletRequest request){
        LoginResponseDto login = userService.login(loginRequestDto);
        HttpSession session = request.getSession(false);
        if(session != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"이미 로그인된 삳태입니다.");
        }
        session = request.getSession();
        session.setAttribute("user", login);

        return new ResponseEntity<>(login,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
