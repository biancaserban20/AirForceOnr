package com.pweb.AirForceOne.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    @NotBlank
    private String departureLocation;
    @NotBlank
    private String arrivalLocation;

    @NotNull
    private ZonedDateTime departureDate;

    @NotNull
    private ZonedDateTime arrivalDate;

    @Min(value = 1)
    private int capacity;

    @Min(value = 0)
    private int occupiedSeats;
}
