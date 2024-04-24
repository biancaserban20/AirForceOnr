package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.service.CabinCrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cabinCrewMember")
public class CabinCrewMemberController {
    private final CabinCrewMemberService cabinCrewMemberService;
}
