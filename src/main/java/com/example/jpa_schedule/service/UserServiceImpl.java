package com.example.jpa_schedule.service;

import com.example.jpa_schedule.UserRepository.UserRepository;
import com.example.jpa_schedule.config.PasswordEncoder;
import com.example.jpa_schedule.dto.user.*;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public SignUpResponseDto signUp(String userEmail, String password, String userName) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userName, userEmail, encodedPassword);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getUserEmail(),
                savedUser.getCreatedAt()
        );
    }

    @Override
    public UserResponseDto findById(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);

        return new UserResponseDto(
                findUser.getUserId(),
                findUser.getUserEmail(),
                findUser.getUserName(),
                findUser.getCreatedAt(),
                findUser.getModifiedAt()
        );
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, UpdatePasswordRequestDto requestDto) {
        User user = userRepository.findByIdOrElseThrow(userId);
        boolean passwordMatch = passwordEncoder.matches(requestDto.getOldPassword(),user.getPassword());
        if (!passwordMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);

        userRepository.delete(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUserEmailOrElseThrow(loginRequestDto.getUserEmail());
        boolean passwordMatch = passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword());
        if (!passwordMatch) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(user.getUserId(), user.getUserEmail(), user.getUserName());

        return loginResponseDto;
    }
}
