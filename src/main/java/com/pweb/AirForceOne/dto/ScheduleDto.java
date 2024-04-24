package com.pweb.AirForceOne.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    @NotBlank
    private String departureLocation;
    @NotBlank
    private String arrivalLocation;
}
