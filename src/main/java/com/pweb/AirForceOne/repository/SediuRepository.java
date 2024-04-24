package com.pweb.AirForceOne.repository;


import com.pweb.AirForceOne.model.Sediu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SediuRepository extends JpaRepository<Sediu, Long> {
}
