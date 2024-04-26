package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.dto.FlightDto;
import com.pweb.AirForceOne.service.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping("/{aeronavaId}")
    public ResponseEntity<Void> createFlight(HttpServletRequest httpServletRequest,@Valid @RequestBody FlightDto flightDto, @PathVariable Long aeronavaId) {
     //   String jwtToken = httpServletRequest.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if (!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        flightService.createFlight(flightDto, aeronavaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data/{flightId}")
    public ResponseEntity<FlightDto> getFlightData(HttpServletRequest http, @PathVariable Long flightId) {
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if (!role.equals("admin") && !role.equals("cabinCrewMember")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        FlightDto flight = flightService.getFlightData(flightId);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFlight(HttpServletRequest http, @RequestBody Map<String, Object> map){
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if (!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        String departureLocation = (String) map.get("departureLocation");
        String arrivalLocation = (String) map.get("arrivalLocation");
        flightService.deleteFlight(departureLocation, arrivalLocation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateFlight(HttpServletRequest http, @Valid @RequestBody FlightDto flightDto, @PathVariable Long id){
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if (!role.equals("admin")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        if(flightDto.getDepartureDate().isBefore(ZonedDateTime.now()) || flightDto.getArrivalDate().isBefore(ZonedDateTime.now())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(flightService.getFlightData(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        flightService.updateFlightDepartureAndArrivalDate(flightDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
