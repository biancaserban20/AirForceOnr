package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.service.SediuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sediu")
public class SediuController {
    private final SediuService sediuService;
}
