package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.AeronavaDto;
import com.pweb.AirForceOne.model.Aeronava;
import com.pweb.AirForceOne.repository.AeronavaRepository;
import com.pweb.AirForceOne.repository.SediuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AeronavaService {
    private final AeronavaRepository aeronavaRepository;
    private final SediuRepository sediuRepository;
    private final ModelMapper modelMapper;

    public List<AeronavaDto> getAllAeronave() {
        return aeronavaRepository.findAll().stream()
                .map(aeronava -> modelMapper.map(aeronava, AeronavaDto.class))
                .toList();
    }

    public void createAeronava(AeronavaDto aeronavaDto, String address) {
        Aeronava aeronava = modelMapper.map(aeronavaDto, Aeronava.class);
        var sediu = sediuRepository.findByAddress(address)
                .orElseThrow(() -> new RuntimeException("Sediu not found"));
        aeronava.setSediu(sediu);
        aeronavaRepository.save(aeronava);
    }

    public void deleteAeronava(LocalDate time) {
        Optional<Aeronava> aeronava = Optional.of(aeronavaRepository.findByFabricationDate(time).orElseThrow());
        aeronavaRepository.delete(aeronava.get());
    }

    public AeronavaDto getAeronava(LocalDate time) {
        Optional<Aeronava> aeronava = Optional.of(aeronavaRepository.findByFabricationDate(time).orElseThrow());
        if(aeronava.isEmpty()) {
            throw new RuntimeException("Aeronava not found");
        }
        return modelMapper.map(aeronava, AeronavaDto.class);
    }
}
