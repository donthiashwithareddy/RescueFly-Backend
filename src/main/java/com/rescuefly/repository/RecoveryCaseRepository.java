package com.rescuefly.repository;
import com.rescuefly.entity.*; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface RecoveryCaseRepository extends JpaRepository<RecoveryCase,Long>{ List<RecoveryCase> findByOwnerOrderByCreatedAtDesc(User owner); }
