package com.example.jpa_schedule.service;

import com.example.jpa_schedule.dto.schedule.ScheduleResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public interface ScheduleService {
    ScheduleResponseDto registSchedule(Long userId,String title,String todo);

    ScheduleResponseDto findById(Long scheduleId);

    ScheduleResponseDto modifyTodo(Long scheduleId,Long userId,String todo);

    void deleteSchedule(Long scheduleId, Long userId);
}
