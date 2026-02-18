package com.claim.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.claim.hospital.entity.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
