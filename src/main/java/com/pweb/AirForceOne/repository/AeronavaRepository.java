package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.Aeronava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AeronavaRepository extends JpaRepository<Aeronava, Long> {
}
