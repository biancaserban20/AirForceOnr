package com.pweb.AirForceOne.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"bookings", "schedules", "aeronava"})
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureLocation;
    private String arrivalLocation;
    private ZonedDateTime departureDate;
    private ZonedDateTime arrivalDate;
    private int capacity;
    private int occupiedSeats;

    @JsonIgnore
    @OneToMany(mappedBy = "flight", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aeronava_id", referencedColumnName = "id", nullable = false)
    private Aeronava aeronava;

    @JsonIgnore
    @OneToMany(mappedBy = "flight", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Schedule> schedules;
}
