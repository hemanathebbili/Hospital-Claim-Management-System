package com.claim.hospital.repository;

import com.claim.hospital.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByPatientId(Long patientId);

    List<Claim> findByStatus(String status);
}
