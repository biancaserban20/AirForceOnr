package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

}