package com.example.jpa_schedule.repository;

import com.example.jpa_schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    default Comment findByIdOrElseThrow(Long commentId){
        return findById(commentId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 댓글입니다."));
    }
}
