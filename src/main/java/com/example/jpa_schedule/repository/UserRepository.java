package com.example.jpa_schedule.repository;


import com.example.jpa_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    default User findByIdOrElseThrow(Long userId){
        return findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 회원입니다."));
    }

    Optional<User> findByUserEmail(String userEmail);

    default User findByUserEmailOrElseThrow(String userEmail){
        return findByUserEmail(userEmail).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 회원입니다."));
    }
}
