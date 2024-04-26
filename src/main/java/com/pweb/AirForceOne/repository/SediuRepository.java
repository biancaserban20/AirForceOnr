package com.pweb.AirForceOne.repository;


import com.pweb.AirForceOne.model.Admin;
import com.pweb.AirForceOne.model.Sediu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SediuRepository extends JpaRepository<Sediu, Long> {
    Optional<Sediu> findByAddress(String address);

    List<Sediu> findByCity(String city);

    List<Sediu> findByCountry(String country);

    @Query(nativeQuery = true,
            value = "SELECT a.id, a.first_name, a.last_name, a.email, a.password, a.created_at" +
                    " FROM admin a JOIN sediu s ON a.sediu_id = s.id WHERE s.address = :address")
    List<Object[]> getAllAdminsRaw(String address);
}