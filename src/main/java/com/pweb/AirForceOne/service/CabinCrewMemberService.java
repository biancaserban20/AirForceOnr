package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.CabinCrewMemberDto;
import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.dto.ScheduleDto;
import com.pweb.AirForceOne.exceptions.ClientExistsException;
import com.pweb.AirForceOne.exceptions.ClientNotFound;
import com.pweb.AirForceOne.exceptions.WrongPasswordException;
import com.pweb.AirForceOne.model.CabinCrewMember;
import com.pweb.AirForceOne.model.Schedule;
import com.pweb.AirForceOne.repository.CabinCrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CabinCrewMemberService {
    private final CabinCrewMemberRepository cabinCrewMemberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public void createCabinCrewMember(CabinCrewMemberDto cabinCrewMemberDto) {
        var dbClient = cabinCrewMemberRepository.findByEmail(cabinCrewMemberDto.getEmail());
        if (dbClient.isPresent()) {
            throw new ClientExistsException();
        }

        var newCabinCrewMember = modelMapper.map(cabinCrewMemberDto, CabinCrewMember.class);
        newCabinCrewMember.setPassword(passwordEncoder.encode(newCabinCrewMember.getPassword()));
        cabinCrewMemberRepository.save(newCabinCrewMember);
    }

    public void loginCabinCrewMember(CabinCrewMemberDto cabinCrewMemberDto){
        var cabinCrewMember = cabinCrewMemberRepository
                .findByEmail(cabinCrewMemberDto.getEmail())
                .orElseThrow(ClientNotFound::new);

        if(!passwordEncoder.matches(cabinCrewMember.getPassword(), cabinCrewMember.getPassword())){
            throw new WrongPasswordException();
        }
    }

    public CabinCrewMemberDto getCabinCrewMemberData(String email){
        return modelMapper.map(
                cabinCrewMemberRepository.findByEmail(email).orElseThrow(ClientNotFound::new),
                CabinCrewMemberDto.class
        );
    }

    public void deleteCabinCrewMember(String email) {
        var cabinCrewMember = cabinCrewMemberRepository.findByEmail(email).orElseThrow(ClientNotFound::new);
        cabinCrewMemberRepository.delete(cabinCrewMember);
    }

    public List<ScheduleDto> getAllSchedules(String email) {

        //var cabinCrewMember = cabinCrewMemberRepository.findByEmail(email).orElseThrow(ClientNotFound::new);
        List<Schedule> schedules = new ArrayList<>();
        for (Object[] rawSchedule : cabinCrewMemberRepository.getAllSchedules(email)) {
            Schedule schedule = new Schedule();
            schedule.setId(((Number) rawSchedule[0]).longValue());
            schedule.setDepartureLocation((String) rawSchedule[1]);
            schedule.setArrivalLocation((String) rawSchedule[2]);
            schedules.add(schedule);
        }
        return schedules.stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDto.class))
                .toList();
    }
}
