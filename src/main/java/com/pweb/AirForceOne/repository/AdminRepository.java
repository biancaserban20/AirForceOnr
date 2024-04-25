package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.Admin;
import com.pweb.AirForceOne.model.CabinCrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    Optional<Admin> findByEmail(String email);
}
