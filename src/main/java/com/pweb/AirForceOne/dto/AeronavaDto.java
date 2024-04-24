package com.pweb.AirForceOne.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AeronavaDto {
    private String type;
    private LocalDate fabricationDate;
    @Positive
    private int capacity;
    private String status;
}
