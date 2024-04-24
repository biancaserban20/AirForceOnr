package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.repository.SediuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SediuService {
    private final SediuRepository sediuRepository;
}
