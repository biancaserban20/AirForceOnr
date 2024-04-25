package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.CabinCrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CabinCrewMemberRepository extends JpaRepository<CabinCrewMember, Long> {
    Optional<CabinCrewMember> findByEmail(String email);
}
