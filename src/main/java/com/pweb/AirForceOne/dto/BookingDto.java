package com.pweb.AirForceOne.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String currency;
    private String seat;
    private String status;
    private ZonedDateTime bookingDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientDto client;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightDto flight;

    @OneToOne(mappedBy = "booking")
    private FeedbackDto feedback;

}
