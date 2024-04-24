package com.pweb.AirForceOne.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureLocation;
    private String arrivalLocation;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int capacity;
    private int occupiedSeats;

    @OneToMany(mappedBy = "flight")
    private Set<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "aeronava_id")
    private Aeronava aeronava;


}
