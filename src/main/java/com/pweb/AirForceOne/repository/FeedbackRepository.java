package com.pweb.AirForceOne.repository;

import com.pweb.AirForceOne.model.Booking;
import com.pweb.AirForceOne.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByBooking(Booking booking);
}
