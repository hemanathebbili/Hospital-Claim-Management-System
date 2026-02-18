package com.claim.hospital.service;

import com.claim.hospital.dto.hospital.HospitalRequestDTO;
import com.claim.hospital.dto.hospital.HospitalResponseDTO;
import com.claim.hospital.entity.Hospital;
import com.claim.hospital.repository.HospitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository repository;
    private final ModelMapper mapper;

    public HospitalServiceImpl(HospitalRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HospitalResponseDTO create(HospitalRequestDTO dto) {
        Hospital hospital = mapper.map(dto, Hospital.class);
        Hospital saved = repository.save(hospital);
        return mapper.map(saved, HospitalResponseDTO.class);
    }

    @Override
    public HospitalResponseDTO getById(Long id) {
        Hospital hospital = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + id));
        return mapper.map(hospital, HospitalResponseDTO.class);
    }

    @Override
    public List<HospitalResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(h -> mapper.map(h, HospitalResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
