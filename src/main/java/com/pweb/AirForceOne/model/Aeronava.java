package com.pweb.AirForceOne.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"flights", "sediu"})
@Entity
public class Aeronava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate fabricationDate;
    private int capacity;
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sediu_id", referencedColumnName = "id", nullable = false)
    private Sediu sediu;

    @JsonIgnore
    @OneToMany(mappedBy = "aeronava", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Flight> flights;
}
