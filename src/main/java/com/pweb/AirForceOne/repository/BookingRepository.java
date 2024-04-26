package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.Booking;
import com.pweb.AirForceOne.model.Client;
import com.pweb.AirForceOne.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClient(Client client);

    Optional<Booking> findByClientAndFlight(Client client, Flight flight);

    @Query(nativeQuery = true,
            value = "SELECT * FROM booking WHERE client_id = :id")
    List<Booking> findAllByClient(Long id);
}
