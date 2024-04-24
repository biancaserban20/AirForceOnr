package com.pweb.AirForceOne.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"admins", "aeronave"})
@Entity
public class Sediu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String country;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "sediu", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Admin> admins;

    @JsonIgnore
    @OneToMany(mappedBy = "sediu", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Aeronava> aeronave;
}
