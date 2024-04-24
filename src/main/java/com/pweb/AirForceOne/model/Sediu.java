package com.pweb.AirForceOne.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sediu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String city;
    private String country;
    private String phone;

    @OneToMany(mappedBy = "sediu")
    private Set<Admin> admins;

    @OneToMany(mappedBy = "sediu")
    private Set<Aeronava> aeronave;
}
