package com.claim.hospital.service;

import com.claim.hospital.dto.hospital.HospitalRequestDTO;
import com.claim.hospital.dto.hospital.HospitalResponseDTO;

import java.util.List;

public interface HospitalService {

    HospitalResponseDTO create(HospitalRequestDTO dto);

    HospitalResponseDTO getById(Long id);

    List<HospitalResponseDTO> getAll();

    void delete(Long id);
}
