package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.BookingDto;
import com.pweb.AirForceOne.dto.FlightDto;
import com.pweb.AirForceOne.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping("/{flightId}")
    public ResponseEntity<Void> createBooking(HttpServletRequest httpServletRequest, @Valid @RequestBody BookingDto bookingDto, @PathVariable Long flightId) {
        String jwtToken = httpServletRequest.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String clientEmail = jwtTokenResolver.getEmailFromToken(jwtToken);
        bookingService.createBooking(bookingDto, clientEmail, flightId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data")
    public ResponseEntity<BookingDto> getAllBookings(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        String clientEmail = jwtTokenResolver.getEmailFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        BookingDto booking = bookingService.getAllBookings(clientEmail);
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    @GetMapping("/data/{flightId}")
    public ResponseEntity<BookingDto> getBookingData(HttpServletRequest http, @PathVariable Long flightId) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        String clientEmail = jwtTokenResolver.getEmailFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        BookingDto booking = bookingService.getBookingData(clientEmail, flightId);
        if(booking == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }
}
