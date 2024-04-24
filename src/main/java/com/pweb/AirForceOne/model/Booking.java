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
@ToString(exclude = {"client", "flight", "feedback"})
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String currency;
    private String seat;
    private String status;
    private ZonedDateTime bookingDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    private Flight flight;

    @JsonIgnore
    @OneToOne(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Feedback feedback;

}
