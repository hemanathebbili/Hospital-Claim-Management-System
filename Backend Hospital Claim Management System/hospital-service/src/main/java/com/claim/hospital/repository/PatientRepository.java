package com.claim.hospital.repository;

import com.claim.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByHospitalId(Long hospitalId);

    List<Patient> findByDisease(String disease);
}
