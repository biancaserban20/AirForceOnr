package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.model.Client;
import com.pweb.AirForceOne.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginClient(@RequestBody ClientDto clientDto) {
        clientService.loginClient(clientDto);
        //TO DO: generate jwt token
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
