package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.CabinCrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CabinCrewMemberRepository extends JpaRepository<CabinCrewMember, Long> {
    Optional<CabinCrewMember> findByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT s.id, s.departure_location, s.arrival_location" +
                    " FROM schedules s JOIN cabin_crew_members c ON s.cabin_crew_member_id = c.id WHERE c.email = :email")
    List<Object[]> getAllSchedules(String email);
}
