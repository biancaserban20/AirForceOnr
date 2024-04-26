package com.pweb.AirForceOne.controller;

import com.pweb.AirForceOne.config.jwt.JwtTokenResolver;
import com.pweb.AirForceOne.dto.AdminDto;
import com.pweb.AirForceOne.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final JwtTokenResolver jwtTokenResolver;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody AdminDto adminDto, @RequestParam String sediuAddress) {
        System.out.println(sediuAddress);
        adminService.createAdmin(adminDto, sediuAddress);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/data")
    public ResponseEntity<AdminDto> getAdminData(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        AdminDto admin = adminService.getAdminData(username);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginClient(@RequestBody Map<String, String> credentials) {
        AdminDto adminDto = new AdminDto("A", "A", credentials.get("email"), credentials.get("password"));
        adminService.loginAdmin(adminDto);
        String jwtToken = jwtTokenResolver.generateJwtToken(adminDto.getEmail(), "admin");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", "Bearer " + jwtToken)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAdmin(HttpServletRequest http) {
        String jwtToken = http.getHeader("Authorization").substring(7);
        String role = jwtTokenResolver.getRoleFromToken(jwtToken);
        if (!role.equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String username = jwtTokenResolver.getEmailFromToken(jwtToken);
        adminService.deleteAdmin(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
