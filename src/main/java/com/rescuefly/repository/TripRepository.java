package com.rescuefly.repository;
import com.rescuefly.entity.*; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface TripRepository extends JpaRepository<Trip,Long>{ List<Trip> findByOwnerOrderByCreatedAtDesc(User owner); long countByOwner(User owner); long countByOwnerAndStatus(User owner, TripStatus status); }
