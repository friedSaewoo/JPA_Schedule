package com.example.jpa_schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    @NotBlank
    private final String title;
    @NotBlank
    private final String todo;

    public ScheduleCreateRequestDto(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }
}
