package com.example.jpa_schedule.service;

import com.example.jpa_schedule.repository.ScheduleRepository;
import com.example.jpa_schedule.repository.UserRepository;
import com.example.jpa_schedule.dto.schedule.ScheduleResponseDto;
import com.example.jpa_schedule.entity.Schedule;
import com.example.jpa_schedule.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ScheduleResponseDto registSchedule(Long userId, String title, String todo) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = new Schedule(title,todo,findUser);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getTodo(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Override
    public ScheduleResponseDto findById(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        return new ScheduleResponseDto(
                findSchedule.getScheduleId(),
                findSchedule.getTitle(),
                findSchedule.getTodo(),
                findSchedule.getCreatedAt(),
                findSchedule.getModifiedAt()
        );
    }

    @Override
    @Transactional
    public void modifyTodo(Long scheduleId, Long userId, String todo) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        if(!findSchedule.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"접근권한이 없습니다.");
        }
        findSchedule.updateTodo(todo);
    }


    @Override
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        if(!schedule.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"접근권한이 없습니다.");
        }
        scheduleRepository.delete(schedule);
    }

    @Override
    public PagedModel<EntityModel<ScheduleResponseDto>> findScheduleList(int page, int size,
                                                                         PagedResourcesAssembler<ScheduleResponseDto> assembler
    ) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByCreatedAtDesc(pageable);
        Page<ScheduleResponseDto> responsePage = schedulePage.map(ScheduleResponseDto::new);
        return assembler.toModel(responsePage);
    }
}
