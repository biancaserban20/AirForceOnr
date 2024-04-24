package com.pweb.AirForceOne.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

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

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<BookingDto> bookings;

}
