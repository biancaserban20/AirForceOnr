package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.service.AeronavaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aeronava")
public class AeronavaController {
    private final AeronavaService aeronavaService;
}
