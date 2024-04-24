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
@ToString(exclude = {"schedules"})
@Entity
public class CabinCrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String position;

    @JsonIgnore
    @OneToMany(mappedBy = "cabinCrewMember", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Schedule> schedules;
}
