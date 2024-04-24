package com.pweb.AirForceOne.service;


import com.pweb.AirForceOne.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;
}
