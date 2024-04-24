package com.pweb.AirForceOne.service;


import com.pweb.AirForceOne.repository.CabinCrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CabinCrewMemberService {
    private final CabinCrewMemberRepository cabinCrewMemberRepository;
}
