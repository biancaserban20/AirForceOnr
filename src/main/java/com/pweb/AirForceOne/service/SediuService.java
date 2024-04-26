package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.AdminDto;
import com.pweb.AirForceOne.dto.SediuDto;
import com.pweb.AirForceOne.model.Admin;
import com.pweb.AirForceOne.model.Sediu;
import com.pweb.AirForceOne.repository.SediuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SediuService {
    private final SediuRepository sediuRepository;
    private final ModelMapper modelMapper;

    public void createSediu(SediuDto sediuDto) {
        var sediu = modelMapper.map(sediuDto, Sediu.class);
        sediuRepository.save(sediu);
    }

    public List<SediuDto> getAllSediiByCity(String city) {
        var sedii = sediuRepository.findByCity(city);
        return sedii.stream()
                .map(sediu -> modelMapper.map(sediu, SediuDto.class))
                .toList();
    }

    public List<SediuDto> getAllSediiByCountry(String country) {
        var sedii = sediuRepository.findByCountry(country);
        return sedii.stream()
                .map(sediu -> modelMapper.map(sediu, SediuDto.class))
                .toList();
    }

    public SediuDto getSediuByAddress(String address) {
        var sediu = sediuRepository.findByAddress(address)
                .orElseThrow();
        return modelMapper.map(sediu, SediuDto.class);
    }

    public List<AdminDto> getAllAdmins(String address) {
        List<Object[]> adminsRaw = sediuRepository.getAllAdminsRaw(address);
        List<Admin> admins = new ArrayList<>();
        for (Object[] rawAdmin : adminsRaw) {
            Admin admin = new Admin();
            admin.setId(((Number) rawAdmin[0]).longValue());
            admin.setFirstName((String) rawAdmin[1]);
            admin.setLastName((String) rawAdmin[2]);
            admin.setEmail((String) rawAdmin[3]);
            admin.setPassword((String) rawAdmin[4]);
            admin.setCreatedAt(null);
            admins.add(admin);
        }
        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .toList();
    }
}
