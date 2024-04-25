package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.model.Client;
import com.pweb.AirForceOne.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginClient(@RequestBody ClientDto clientDto) {
        clientService.loginClient(clientDto);
        String jwtToken = jwtTokenResolver.generateJwtToken(clientDto.getEmail(), "client");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", "Bearer " + jwtToken)
                .build();
    }

    @GetMapping("/data")
    public ResponseEntity<ClientDto> getClientData(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("client")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        ClientDto client = clientService.getClientData(username);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

}
