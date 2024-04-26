package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.dto.FeedbackDto;
import com.pweb.AirForceOne.service.FeedbackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping
    public ResponseEntity<Void> createFeedback(HttpServletRequest http, @Valid @RequestBody FeedbackDto feedbackDto, @RequestParam Long id) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        feedbackService.createFeedback(feedbackDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackData(HttpServletRequest http, @PathVariable Long id) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        List<FeedbackDto> feedbacks = feedbackService.getFeedbackByBookingId(id);
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

}
