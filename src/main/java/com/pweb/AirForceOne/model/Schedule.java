package com.pweb.AirForceOne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"flight", "cabinCrewMember"})
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureLocation;
    private String arrivalLocation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    private Flight flight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cabincrewmember_id", referencedColumnName = "id", nullable = false)
    private CabinCrewMember cabinCrewMember;
}
