package com.example.jpa_schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    @NotBlank(message="title is empty")
    @Size(max=100,message = "제목을 100자 이내로 입력해주세요")
    private final String title;
    @NotBlank(message = "todo is empty")
    private final String todo;

    public ScheduleCreateRequestDto(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }
}
