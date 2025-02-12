package com.example.jpa_schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleTodoModifyDto {
    @NotBlank
    @Size(max = 200, message = "할 일은 200자 이내로 입력하세요")
    private final String Todo;

    public ScheduleTodoModifyDto(String todo) {
        Todo = todo;
    }
}
