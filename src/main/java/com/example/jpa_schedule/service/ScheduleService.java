package com.example.jpa_schedule.service;

import com.example.jpa_schedule.dto.schedule.ScheduleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface ScheduleService {
    ScheduleResponseDto registSchedule(Long userId,String title,String todo);

    ScheduleResponseDto findById(Long scheduleId);

    ScheduleResponseDto modifyTodo(Long scheduleId,Long userId,String todo);

    void deleteSchedule(Long scheduleId, Long userId);

    PagedModel<EntityModel<ScheduleResponseDto>> findScheduleList(int page, int size, PagedResourcesAssembler<ScheduleResponseDto> assembler);
}
