package com.pweb.AirForceOne.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScheduleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureLocation;
    private String arrivalLocation;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightDto flight;

    @ManyToOne
    @JoinColumn(name = "cabincrewmember_id")
    private CabinCrewMemberDto cabinCrewMember;
}
