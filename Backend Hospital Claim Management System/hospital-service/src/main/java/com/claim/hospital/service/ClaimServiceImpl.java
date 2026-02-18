package com.claim.hospital.service;

import com.claim.hospital.constants.ClaimStatus;
import com.claim.hospital.dto.claim.ClaimRequestDTO;
import com.claim.hospital.dto.claim.ClaimResponseDTO;
import com.claim.hospital.entity.Claim;
import com.claim.hospital.repository.ClaimRepository;
//import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository repository;
    private final ModelMapper mapper;
    
    


    public ClaimServiceImpl(ClaimRepository repository, ModelMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}


	// =========================================
    // SUBMIT CLAIM
    // =========================================
    @Override
    public ClaimResponseDTO submitClaim(ClaimRequestDTO dto) {

        Claim claim = mapper.map(dto, Claim.class);

        // default status when created
        claim.setStatus(ClaimStatus.PENDING);

        Claim saved = repository.save(claim);

        return mapper.map(saved, ClaimResponseDTO.class);
    }


    // =========================================
    // GET BY ID
    // =========================================
    @Override
    public ClaimResponseDTO getById(Long id) {

        Claim claim = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        return mapper.map(claim, ClaimResponseDTO.class);
    }


    // =========================================
    // GET ALL
    // =========================================
    @Override
    public List<ClaimResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(c -> mapper.map(c, ClaimResponseDTO.class))
                .collect(Collectors.toList());
    }


    // =========================================
    // UPDATE STATUS
    // =========================================
    @Override
    public ClaimResponseDTO updateStatus(Long id, String status) {

        Claim claim = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(status);

        Claim updated = repository.save(claim);

        return mapper.map(updated, ClaimResponseDTO.class);
    }


    // =========================================
    // DELETE
    // =========================================
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
