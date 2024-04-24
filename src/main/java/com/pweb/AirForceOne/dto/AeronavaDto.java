package com.pweb.AirForceOne.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AeronavaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate fabricationDate;
    private int capacity;
    private String status;

    @ManyToOne
    @JoinColumn(name = "sediu_id")
    private SediuDto sediu;

    @OneToMany(mappedBy = "aeronava")
    private Set<FlightDto> flights;
}
