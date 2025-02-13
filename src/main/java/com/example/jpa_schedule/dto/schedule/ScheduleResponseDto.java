package com.example.jpa_schedule.dto.schedule;

import com.example.jpa_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String todo;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleResponseDto(Long scheduleId, String title, String todo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.todo = schedule.getTodo();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
