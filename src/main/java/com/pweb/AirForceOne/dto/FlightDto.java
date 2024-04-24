package com.pweb.AirForceOne.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureLocation;
    private String arrivalLocation;
    private ZonedDateTime departureDate;
    private ZonedDateTime arrivalDate;
    private int capacity;
    private int occupiedSeats;

    @OneToMany(mappedBy = "flight")
    private Set<BookingDto> bookings;

    @ManyToOne
    @JoinColumn(name = "aeronava_id")
    private AeronavaDto aeronava;

    @OneToMany(mappedBy = "flight")
    private Set<ScheduleDto> schedules;
}
