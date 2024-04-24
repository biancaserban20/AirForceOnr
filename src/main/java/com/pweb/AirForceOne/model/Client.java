package com.pweb.AirForceOne.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain letters and spaces")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain letters and spaces")
    private String firstName;

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit.")
    private String password;

    @OneToMany(mappedBy = "client")
    private Set<Booking> bookings;

}
