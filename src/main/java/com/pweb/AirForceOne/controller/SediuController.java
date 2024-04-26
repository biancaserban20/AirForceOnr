package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.SediuDto;
import com.pweb.AirForceOne.service.SediuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sediu")
public class SediuController {
    private final SediuService sediuService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping
    public ResponseEntity<Void> createSediu(HttpServletRequest http, @Valid @RequestBody SediuDto sediuDto) {
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if(!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        sediuService.createSediu(sediuDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getAllSediiByCity(@PathVariable String city) {
        if(sediuService.getAllSediiByCity(city).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(sediuService.getAllSediiByCity(city));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<?> getAllSediiByCountry(@PathVariable String country) {
        if(sediuService.getAllSediiByCountry(country).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(sediuService.getAllSediiByCountry(country));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllAdmins(HttpServletRequest http, @RequestParam String address){
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if(!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        if(sediuService.getAllAdmins(address).isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(sediuService.getAllAdmins(address));
    }
}
