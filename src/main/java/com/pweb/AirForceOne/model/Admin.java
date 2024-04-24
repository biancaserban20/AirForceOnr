package com.pweb.AirForceOne.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "sediu")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    @Column(unique = true, updatable = false)
    private String email;
    private String password;
    private ZonedDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sediu_id", referencedColumnName = "id", nullable = false)
    private Sediu sediu;
}
