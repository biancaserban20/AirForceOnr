package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.BookingDto;
import com.pweb.AirForceOne.model.Booking;
import com.pweb.AirForceOne.repository.BookingRepository;
import com.pweb.AirForceOne.repository.ClientRepository;
import com.pweb.AirForceOne.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public void createBooking(BookingDto bookingDto, String clientEmail, Long flightId) {
        var client = clientRepository.findByEmail(clientEmail).orElseThrow();
        var flight = flightRepository.findById(flightId).orElseThrow();
        var booking = modelMapper.map(bookingDto, Booking.class);
        booking.setClient(client);
        booking.setFlight(flight);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        var booking = bookingRepository.findById(bookingId);
        booking.ifPresent(bookingRepository::delete);
    }

    public BookingDto getAllBookings(String clientEmail) {
        var client = clientRepository.findByEmail(clientEmail).orElseThrow();
        return modelMapper.map(bookingRepository.findByClient(client), BookingDto.class);
    }

    public BookingDto getBookingData(String clientEmail, Long flightId) {
        var client = clientRepository.findByEmail(clientEmail).orElseThrow();
        var flight = flightRepository.findById(flightId).orElseThrow();
        return modelMapper.map(bookingRepository.findByClientAndFlight(client, flight), BookingDto.class);
    }
}
