package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.AdminDto;
import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.exceptions.ClientExistsException;
import com.pweb.AirForceOne.exceptions.ClientNotFound;
import com.pweb.AirForceOne.exceptions.WrongPasswordException;
import com.pweb.AirForceOne.model.Admin;
import com.pweb.AirForceOne.repository.AdminRepository;
import com.pweb.AirForceOne.repository.SediuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final SediuRepository sediuRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void createAdmin(AdminDto adminDto, String sediuAddress) {
        var dbAdmin = adminRepository.findByEmail(adminDto.getEmail());
        if (dbAdmin.isPresent()) {
            throw new ClientExistsException();
        }

        var newAdmin = modelMapper.map(adminDto, Admin.class);
        newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
        newAdmin.setCreatedAt(ZonedDateTime.now());
        newAdmin.setSediu(sediuRepository.findByAddress(sediuAddress).get());
        adminRepository.save(newAdmin);
    }

    public AdminDto getAdminData(String email) {
        return modelMapper.map(
                adminRepository.findByEmail(email).orElseThrow(ClientNotFound::new),
                AdminDto.class
        );
    }

    public void loginAdmin(AdminDto adminDto){
        var admin = adminRepository
                .findByEmail(adminDto.getEmail())
                .orElseThrow(ClientNotFound::new);

        if(!passwordEncoder.matches(adminDto.getPassword(), admin.getPassword())){
            throw new WrongPasswordException();
        }
    }

    public void deleteAdmin(String email) {
        var admin = adminRepository.findByEmail(email).orElseThrow(ClientNotFound::new);
        adminRepository.delete(admin);
    }

}
