package com.claim.hospital.service;

import com.claim.hospital.dto.claim.ClaimRequestDTO;
import com.claim.hospital.dto.claim.ClaimResponseDTO;

import java.util.List;

public interface ClaimService {

    ClaimResponseDTO submitClaim(ClaimRequestDTO dto);

    ClaimResponseDTO getById(Long id);

    List<ClaimResponseDTO> getAll();

    ClaimResponseDTO updateStatus(Long id, String status);

    void delete(Long id);
}
