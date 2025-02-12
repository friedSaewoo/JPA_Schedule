package com.example.jpa_schedule.controller;

import com.example.jpa_schedule.dto.SignUpRequestDto;
import com.example.jpa_schedule.dto.SignUpResponseDto;
import com.example.jpa_schedule.dto.UpdatePasswordRequestDto;
import com.example.jpa_schedule.dto.UserResponseDto;
import com.example.jpa_schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
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
                                               @RequestBody UpdatePasswordRequestDto requestDto){

        userService.updatePassword(userId,requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId){

        userService.delete(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
