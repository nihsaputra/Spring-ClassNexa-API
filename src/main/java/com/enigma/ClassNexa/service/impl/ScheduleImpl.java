package com.enigma.ClassNexa.service.impl;

import com.enigma.ClassNexa.entity.Schedule;
import com.enigma.ClassNexa.repository.ScheduleRepository;
import com.enigma.ClassNexa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    @Override
    public Schedule getByIdSchedule(String id) {
        Optional<Schedule> byId = scheduleRepository.findById(id);
        Schedule schedule = Schedule.builder()
                .id(byId.get().getId())
                .meetingLink(byId.get().getMeetingLink())
                .startClass(byId.get().getStartClass())
                .endClass(byId.get().getEndClass())
                .Classes(byId.get().getClasses())
                .build();
        return schedule;

    }
}
