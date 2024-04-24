package com.pweb.AirForceOne.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SediuDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String city;
    private String country;
    private String phone;

    @OneToMany(mappedBy = "sediu")
    private Set<AdminDto> admins;

    @OneToMany(mappedBy = "sediu")
    private Set<AeronavaDto> aeronave;
}
