package com.claim.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.claim.hospital.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
