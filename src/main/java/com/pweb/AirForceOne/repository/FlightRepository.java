package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM flight WHERE departure_location = :departureLocation AND arrival_location = :arrivalLocation")
    Optional<Flight> findByDepartureLocationAndArrivalLocation(String departureLocation, String arrivalLocation);
}
