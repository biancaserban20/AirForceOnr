package com.pweb.AirForceOne.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "aeronava_id")
    private Aeronava aeronava;

    @ManyToOne
    @JoinColumn(name = "cabincrewmember_id")
    private CabinCrewMember cabinCrewMember;
}
