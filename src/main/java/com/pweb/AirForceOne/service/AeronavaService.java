package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.repository.AeronavaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AeronavaService {
    private final AeronavaRepository aeronavaRepository;
}
