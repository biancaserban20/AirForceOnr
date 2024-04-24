package com.pweb.AirForceOne.model;


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
public class Aeronava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate fabricationDate;
    private int capacity;
    private String status;

    @ManyToOne
    @JoinColumn(name = "sediu_id")
    private Sediu sediu;

    @OneToMany(mappedBy = "aeronava")
    private Set<Flight> flights;

    @OneToMany(mappedBy = "aeronava")
    private Set<Schedule> schedules;
}
