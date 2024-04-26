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

import java.util.Map;

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
    public ResponseEntity<Void> loginClient(@RequestBody Map<String, String> credentials) {
        ClientDto clientDto = new ClientDto("A", "A", credentials.get("email"), credentials.get("password"));
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

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteClient(HttpServletRequest http, @RequestBody Map<String, String> map) {
//        String jwtToken = http.getHeader("Authorization").substring(7);
//        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
//        if (!role.equals("client")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        clientService.deleteClient(map.get("email"));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
