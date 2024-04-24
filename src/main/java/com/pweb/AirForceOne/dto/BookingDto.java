package com.pweb.AirForceOne.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    @Positive
    private double price;

    @NotBlank
    private String currency;

    @NotBlank
    private String seat;
    private String status;
    private ZonedDateTime bookingDate;
}
