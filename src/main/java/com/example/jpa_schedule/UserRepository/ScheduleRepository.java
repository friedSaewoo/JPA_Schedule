package com.example.jpa_schedule.UserRepository;

import com.example.jpa_schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    default Schedule findByIdOrElseThrow(Long scheduleId){
        return findById(scheduleId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 일정입니다."));
    }
    Page<Schedule> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
