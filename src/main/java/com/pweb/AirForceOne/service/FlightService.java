package com.pweb.AirForceOne.service;


import com.pweb.AirForceOne.dto.FlightDto;
import com.pweb.AirForceOne.model.Flight;
import com.pweb.AirForceOne.repository.AeronavaRepository;
import com.pweb.AirForceOne.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AeronavaRepository aeronavaRepository;
    private final ModelMapper modelMapper;

    public void createFlight(FlightDto flightDto, Long id) {
        var flight = modelMapper.map(flightDto, Flight.class);
        flight.setAeronava(aeronavaRepository.findById(id).orElseThrow());
        flightRepository.save(flight);
    }

    public void deleteFlight(String departureLocation, String arrivalLocation) {
        var flight = flightRepository.findByDepartureLocationAndArrivalLocation(departureLocation, arrivalLocation).get();
        if (flight == null) {
            throw new RuntimeException("Flight not found");
        }
        flightRepository.delete(flight);
    }

    public void updateFlightDepartureAndArrivalDate(FlightDto flightDto, Long id) {
        var flight = flightRepository.findById(id).orElseThrow();
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setArrivalDate(flightDto.getArrivalDate());
        flightRepository.save(flight);
    }


    public FlightDto getFlightData(Long flightId) {
        return modelMapper.map(flightRepository.findById(flightId).orElseThrow(), FlightDto.class);
    }
}
