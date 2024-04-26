package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.AeronavaDto;
import com.pweb.AirForceOne.service.AeronavaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aeronava")
public class AeronavaController {
    private final AeronavaService aeronavaService;
    private final JwtTokenResolver jwtTokenResolver;

    @GetMapping("/all")
    public ResponseEntity<List<AeronavaDto>> getAllAeronave(HttpServletRequest http) {
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if(!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        return ResponseEntity.ok(aeronavaService.getAllAeronave());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createAeronava(HttpServletRequest http, @Valid @RequestBody AeronavaDto aeronavaDto, @RequestParam String address) {
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if(!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        aeronavaService.createAeronava(aeronavaDto, address);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAeronava(@RequestParam LocalDate time) {
        //        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if(!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        aeronavaService.createAeronava(aeronavaDto, address);
        aeronavaService.deleteAeronava(time);
        return ResponseEntity.ok().build();
    }
}
