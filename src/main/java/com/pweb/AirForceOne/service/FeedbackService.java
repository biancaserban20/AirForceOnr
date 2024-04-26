package com.pweb.AirForceOne.service;


import com.pweb.AirForceOne.dto.BookingDto;
import com.pweb.AirForceOne.dto.FeedbackDto;
import com.pweb.AirForceOne.model.Feedback;
import com.pweb.AirForceOne.repository.BookingRepository;
import com.pweb.AirForceOne.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public void createFeedback(FeedbackDto feedbackDto, Long bookingId) {
        var feedback = modelMapper.map(feedbackDto, Feedback.class);
        var booking = bookingRepository.findById(bookingId).
                orElseThrow(() -> new RuntimeException("Booking not found"));
        feedback.setBooking(booking);
        feedbackRepository.save(feedback);
    }

    public List<FeedbackDto> getFeedbackByBookingId(Long bookingId) {
        var booking = bookingRepository.findById(bookingId).
                orElseThrow(() -> new RuntimeException("Booking not found"));
        List<Feedback> feedbackList = feedbackRepository.findByBooking(booking);
        return feedbackList.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackDto.class))
                .toList();
    }
}
