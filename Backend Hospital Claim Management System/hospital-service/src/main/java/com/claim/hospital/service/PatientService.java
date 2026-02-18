package com.claim.hospital.service;

import com.claim.hospital.dto.patient.PatientRequestDTO;
import com.claim.hospital.dto.patient.PatientResponseDTO;
import java.util.List;

public interface PatientService {

    PatientResponseDTO create(PatientRequestDTO dto);

    PatientResponseDTO getById(Long id);

    List<PatientResponseDTO> getAll();

    void delete(Long id);
}
