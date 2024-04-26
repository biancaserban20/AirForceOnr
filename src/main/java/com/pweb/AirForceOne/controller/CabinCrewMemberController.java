package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.CabinCrewMemberDto;
import com.pweb.AirForceOne.dto.ScheduleDto;
import com.pweb.AirForceOne.service.CabinCrewMemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cabinCrewMember")
public class CabinCrewMemberController {
    private final CabinCrewMemberService cabinCrewMemberService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping
    public ResponseEntity<Void> createCabinCrewMember(CabinCrewMemberDto cabinCrewMemberDto) {
        cabinCrewMemberService.createCabinCrewMember(cabinCrewMemberDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data")
    public ResponseEntity<CabinCrewMemberDto> getcabinCrewMemberData(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("cabinCrewMember")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        CabinCrewMemberDto cabinCrewMember = cabinCrewMemberService.getCabinCrewMemberData(username);
        return ResponseEntity.status(HttpStatus.OK).body(cabinCrewMember);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginClient(@RequestBody Map<String, String> credentials) {
        CabinCrewMemberDto cabinCrewMemberDto = new CabinCrewMemberDto("A", "A", credentials.get("email"), credentials.get("password"), "-");
        cabinCrewMemberService.loginCabinCrewMember(cabinCrewMemberDto);
        String jwtToken = jwtTokenResolver.generateJwtToken(cabinCrewMemberDto.getEmail(), "cabinCrewMember");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", "Bearer " + jwtToken)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletecabinCrewMember(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("cabinCrewMember")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        cabinCrewMemberService.deleteCabinCrewMember(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("cabinCrewMember")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        List<ScheduleDto> schedules = cabinCrewMemberService.getAllSchedules(username);
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }
}
